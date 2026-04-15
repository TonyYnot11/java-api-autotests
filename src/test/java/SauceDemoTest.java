import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Story;
import io.qameta.allure.Description;
import org.example.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SauceDemoTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private CheckOutPage checkOutPage;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkOutPage = new CheckOutPage(driver);
    }

    @Story("UI Test")
    @Description("Different User Login")
    @ParameterizedTest
    @CsvSource({
            "standard_user, secret_sauce, success",
            "locked_out_user, secret_sauce, error",
            "problem_user, secret_sauce, success",
            "performance_glitch_user, secret_sauce, success"
    })
    public void loginTest(String username, String password, String expectedResult) {
        loginPage.open();
        loginPage.login(username , password);
        if (expectedResult.equals("success")) {
            assertTrue(driver.getCurrentUrl().contains("inventory.html"));
        } else {
            assertEquals("Epic sadface: Sorry, this user has been locked out.", loginPage.getErrorMessage());
        }
        System.out.println("✅ Тест пройден. ");
    }

    @Story("UI Test")
    @Description("Succses User Login")
    @Test
    public void successfulLoginTest() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        String actualTitle = productsPage.getTitle();
        assertEquals("Products", actualTitle, "Заголовок не совпадает");
        String actualUrl = productsPage.getCurrentUrl();

        System.out.println("✅ Тест пройден. ");
    }

    @Story("UI Test")
    @Description("Locked Out User Login")
    @Test
    public void lockedOutUserTest() {
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");

        String error = loginPage.getErrorMessage();
        assertTrue(error.contains("Sorry, this user has been locked out"));
        System.out.println("✅ Тест пройден. ");
    }


    @Story("UI Test")
    @Description("Add to cart + cart badge count")
    @Test
    public void addToCartTest(){
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        assertEquals("1" ,cartPage.getCartItemCount());
        System.out.println("✅ Тест пройден.");
    }

    @Story("UI Test")
    @Description("Filter from low to high price test")
    @Test
    public void loHiFilter(){
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.selectSortOption("lohi");
        List<Double> prices = productsPage.getProductPrices();
        for (int i = 0; i < prices.size() - 1; i++) {
            assertTrue(prices.get(i) <= prices.get(i + 1), "Цены не отсортированы: " + prices.get(i) + " > " + prices.get(i + 1));
        }
        System.out.println("✅ Тест пройден.");
    }

    @Story("UI Test")
    @Description("adding 2 positions to cart")
    @Test
    public void addTwoProductsToCartTest() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.addToCart("Sauce Labs Bike Light");
        String itemCount = cartPage.getCartItemCount();
        assertEquals("2", itemCount);
        System.out.println("✅ Тест пройден.");
    }

    @Story("UI Test")
    @Description("Cart items approving")
    @Test
    public void cartListApprove(){
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.addToCart("Sauce Labs Bike Light");
        String itemCount = cartPage.getCartItemCount();
        assertEquals("2", itemCount);
        cartPage.openCart();
        List<String> actualList = cartPage.getCartItemNames();
        List<String> expectedList = Arrays.asList("Sauce Labs Backpack" ,"Sauce Labs Bike Light" ) ;
        assertEquals(actualList , expectedList);
        System.out.println("✅ Тест пройден.");
    }

    @Story("UI Test")
    @Description("delete item from cart")
    @Test

    public void deleteFromCart(){
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.addToCart("Sauce Labs Bike Light");
        cartPage.openCart();
        cartPage.removeFromCartByProductName("Sauce Labs Backpack");
        String itemCount = cartPage.getCartItemCount();
        assertEquals("1", itemCount);
        List<String> actualList = cartPage.getCartItemNames();
        List<String> expectedList = Arrays.asList("Sauce Labs Bike Light" ) ;
        assertEquals(actualList , expectedList);
        System.out.println("✅ Тест пройден.");
    }


    @Story("UI Test")
    @Description("Success screen")
    @Test

    public void successScreen(){
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Bike Light");
        cartPage.openCart();
        checkOutPage.clickCheckOut();
        checkOutPage.enterFirstName("Tony");
        checkOutPage.enterLastName("Ynot");
        checkOutPage.enterPostalCode("348738");
        checkOutPage.clickContinue();
        System.out.println(driver.getCurrentUrl());
        checkOutPage.clickFinish();
        System.out.println("clickFinish введён");
        List<String> actualText = checkOutPage.getCompleteMessage();
        List<String> expectedText = Arrays.asList(
                "Thank you for your order!",
                "Your order has been dispatched, and will arrive just as fast as the pony can get there!"
        );
        assertEquals(expectedText, actualText);
        System.out.println("✅ Тест пройден.");
    }


    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}