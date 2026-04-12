import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Story;
import io.qameta.allure.Description;
import org.example.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SauceDemoTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private ProductsPage productsPage;
    private CartPage cartPage;

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
        assertTrue(actualUrl.contains("inventory.html"));
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
        cartPage.addBackpackToCart();
        assertEquals("1" ,cartPage.getCartItemCount());
        System.out.println("✅ Тест пройден. ");


    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}