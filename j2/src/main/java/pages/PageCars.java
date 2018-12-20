package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageCars {
    private WebDriver driver;

    @FindBy(id = "carRadioSelector")
    private WebElement radioButtonCars;

    @FindBy(id = "carOneWayRentalConfirm")
    private WebElement checkBoxReturning;

    @FindBy(id = "returningToDifferentLocation")
    private WebElement divForNameLocation;

    @FindBy(css = ".errorList>li")
    private WebElement error;

    @FindBy(name = "Continue")
    private WebElement buttonContinue;

    public PageCars(WebDriver driver) {
        this.driver = driver;
        driver.get("https://www.britishairways.com/en-us/flights-and-holidays/flights");
        PageFactory.initElements(driver, this);
    }

    public void clickRadioButtonCars() {
        new Actions(driver).moveToElement(radioButtonCars).click().build().perform();
    }

    public void clickCheckBoxReturning() {
        if (!checkBoxReturning.isSelected()) {
            new Actions(driver).moveToElement(checkBoxReturning).click().build().perform();
        }
    }

    public boolean divForNameLocationIsDisabled() {
        return divForNameLocation.isDisplayed();
    }

    public void clickButtonContinue() {
        // new WebDriverWait(driver,
        // 5).until(ExpectedConditions.visibilityOf(buttonContinue));
        buttonContinue.click();
    }

    public String getError() {
        return error.getText();
    }
}
