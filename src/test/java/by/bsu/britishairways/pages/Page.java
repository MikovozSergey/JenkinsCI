package by.bsu.britishairways.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Page {
    protected WebDriver driver;

    @FindBy(name = "Continue")
    private WebElement buttonContinue;

    public Page(WebDriver driver) {
        this.driver = driver;
        driver.get("https://www.britishairways.com/en-us/flights-and-holidays/flights");
    }

    public void clickButtonContinue() {
        try {
            buttonContinue.click();
        } catch (NoSuchElementException e) {
        }
    }

    public void openInputForm() {
        boolean isFormVisible = false;
        while (!isFormVisible) {
            try {
                isFormVisible = driver.findElement(By.xpath("//div[@class='planTripFormContent']")).isDisplayed();
            } catch (NoSuchElementException e) {
                driver.findElement(By.xpath("//a[@href='/en-us/flights-and-holidays/flights']")).click();
            }
        }
    }
}
