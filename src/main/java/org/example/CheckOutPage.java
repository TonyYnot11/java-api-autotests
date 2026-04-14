package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CheckOutPage {

    private WebDriver driver;
    private WebDriverWait wait;




    //Locators

    private By firstNameField = By.id("first-name");
    private By secondNameField = By.id("last-name");
    private By postalCodeField = By.id("postal-code");
    private By  checkOutButton= By.id("checkout");
    private By  continueButton= By.id("continue");
    private By continueShoppingButton = By.id("continue-shopping");
    private By finishButton = By.id("finish");
    private By cancelButton = By.id("cancel");
    private By successMessageText = By.className("complete-text");
    private By succesHeader = By.className("complete-header");

//    public CheckOutPage(WebDriver driver) {
//        this.driver = driver;
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//    }

    public void enterFirstName(String firstName){
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
        driver.findElement(firstNameField).sendKeys(firstName);}

    public void enterLastName(String lastName){driver.findElement(secondNameField).sendKeys(lastName);}

    public void enterPostalCode(String postalCode){driver.findElement(postalCodeField).sendKeys(postalCode);}

    public void clickCheckOut(){wait.until(ExpectedConditions.visibilityOfElementLocated(checkOutButton)).click();}

    public void clickContinue(){wait.until(ExpectedConditions.visibilityOfElementLocated(continueButton)).click();}

    public void clickFinish(){wait.until(ExpectedConditions.visibilityOfElementLocated(finishButton)).click();}

    public List<String> getCompleteMessage(){
        List<String> texts = new ArrayList<>();
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessageText));
        texts.add(successMessageText.toString());
        wait.until(ExpectedConditions.visibilityOfElementLocated(succesHeader));
        texts.add(succesHeader.toString());
        return texts;
    }

}
