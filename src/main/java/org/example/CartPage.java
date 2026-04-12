package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CartPage {

    private WebDriver driver;
    private WebDriverWait wait;

// Локаторы
    private By addToCart = By.id("add-to-cart-sauce-labs-backpack");
    private By cartBage = By.className("shopping_cart_badge");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public void addBackpackToCart(){
       wait.until(ExpectedConditions.visibilityOfElementLocated(addToCart)).click();
    }

    public String getCartItemCount(){

       return driver.findElement(cartBage).getText();
    }
}
