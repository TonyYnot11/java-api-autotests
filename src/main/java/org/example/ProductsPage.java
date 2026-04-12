package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProductsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By title = By.className("title");
    private By filter = By.className("product_sort_container");
    private By price = By.className("inventory_item_price");
    private By addToCartButton(String productName) {
        String id = "add-to-cart-" + productName.toLowerCase().replace(" ", "-");
        return By.id(id);
    }

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(title)).getText();
    }

    public String getCurrentUrl(){ return driver.getCurrentUrl();}

    public void selectSortOption(String option){
        Select dropdown = new Select(driver.findElement(filter));
        dropdown.selectByValue(option);
    }

    public List<Double> getProductPrices(){
        List<Double> prices = new ArrayList<>();
        List<WebElement> priceElements = driver.findElements(price);

        for (WebElement element : priceElements) {
            String priceText = element.getText();
            String priceWithoutDollar = priceText.replace("$", "");
            double price = Double.parseDouble(priceWithoutDollar);
            prices.add(price);
        }
        return prices;
    }
    public void addToCart(String productName) {
        By button = addToCartButton(productName);
        wait.until(ExpectedConditions.elementToBeClickable(button)).click();
    }
}