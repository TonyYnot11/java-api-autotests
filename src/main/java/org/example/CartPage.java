package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


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

    public void openCart(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartLink)).click();
    }

    public String getCartItemCount(){
       return driver.findElement(cartBadge).getText();
    }

    public void addToCartByProductName(String productName){
        String id = "add-to-cart-" + productName.toLowerCase().replace(" ", "-");
        wait.until(ExpectedConditions.elementToBeClickable(By.id(id))).click();
    }

    public List<String> getCartItemNames(){
        List<String> itemNames = new ArrayList<>();
        List<WebElement> nameElements = driver.findElements(By.className("inventory_item_name"));
        for (WebElement element : nameElements) {
            itemNames.add(element.getText());
        }
        return itemNames;
    }

    public void removeFromCartByProductName(String productName){
        String id = "remove-" + productName.toLowerCase().replace(" ", "-");
        wait.until(ExpectedConditions.elementToBeClickable(By.id(id))).click();
    }

}
