import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.LoginPage;
import org.example.ProductsPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SauceDemoTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private ProductsPage productsPage;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
    }

    @Test
    public void successfulLoginTest() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        String actualTitle = productsPage.getTitle();
        assertEquals("Products", actualTitle, "Заголовок не совпадает");
    }

    @Test
    public void lockedOutUserTest() {
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");

        String error = loginPage.getErrorMessage();
        assertTrue(error.contains("Sorry, this user has been locked out"));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}