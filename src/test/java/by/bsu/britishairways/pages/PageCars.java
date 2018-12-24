package by.bsu.britishairways.some;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageCars extends Page {
    @FindBy(xpath = "//label[@for='carRadioSelector']")
    private WebElement radioButtonCars;

    @FindBy(id = "carOneWayRentalConfirm")
    private WebElement checkBoxReturning;

    @FindBy(id = "returningToDifferentLocation")
    private WebElement divForNameLocation;

    @FindBy(css = ".errorList>li")
    private WebElement error;

    @FindBy(xpath = "//div[@class='planTripFormContent']")
    private WebElement planATripForm;

    @FindBy(xpath = "//a[@href='/en-us/flights-and-holidays/flights']")
    private WebElement flightsMenuButton;

    public PageCars(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickRadioButtonCars() {
        radioButtonCars.click();
    }

    public void clickCheckBoxReturning() {
        if (!checkBoxReturning.isSelected()) {
            new Actions(driver).moveToElement(checkBoxReturning).click().build().perform();
        }
    }

    public boolean divForNameLocationIsDisabled() {
        return divForNameLocation.isDisplayed();
    }

    public String getError() {
        return error.getText();
    }
}
