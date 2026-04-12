package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class CartPage {


    private WebDriver driver;
    private WebDriverWait wait;


// Локаторы
    private By cartBadge = By.className("shopping_cart_badge");
    private By cartLink = By.className("shopping_cart_link");
    private By filter = By.className("product_sort_container");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public void addRandomToCart(){
       wait.until(ExpectedConditions.visibilityOfElementLocated(cartLink)).click();
    }

    public String getCartItemCount(){
       return driver.findElement(cartBadge).getText();
    }

    public void addToCartByProductName(String productName){




    }
}
