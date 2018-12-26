package by.bsu.britishairways.pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageHotels extends Page {
    @FindBy(id = "hotelRadioSelector")
    private WebElement radioButtonHotels;

    @FindBy(id = "planTripHotelDestination")
    private WebElement inputDestination;

    @FindBy(id = "hotelSearchButtonHS")
    private WebElement butSearchHotels;

    @FindBy(name = "checkInGO")
    private WebElement inputCheckIn;

    @FindBy(name = "checkOutGO")
    private WebElement inputCheckOut;

    @FindBy(id = "adultsRoom1H")
    private WebElement inputAdultsInRoom;

    @FindBy(id = "infantsRoom1H")
    private WebElement inputInfantsInRoom;

    @FindBy(id = "childrenRoom1H")
    private WebElement inputChildrenInRoom;

    @FindBy(id = "numNightsGO")
    private WebElement countNights;

    @FindBy(css = "#checkInGO_table>tbody")
    private WebElement tableCheckIn;

    @FindBy(css = "#checkOutGO_table>tbody")
    private WebElement tableCheckOut;

    @FindBy(css = ".errorList>li")
    private WebElement error;

    public PageHotels(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickRadioButtonHotels() {
        new Actions(driver).moveToElement(radioButtonHotels).click().build().perform();
    }

    public void setInputDestination(String place) {
        inputDestination.sendKeys(place);
    }

    public void setInputAdultsInRoom(String number) {
        new Select(inputAdultsInRoom).selectByValue(number);
    }

    public void setInputChildrenInRoom(String number) {
        new Select(inputChildrenInRoom).selectByValue(number);
    }

    public int foundCountInputInfantsInRoom() {
        List<WebElement> list = new Select(inputInfantsInRoom).getOptions();
        return list.size();
    }

    private void openCheckInCalendar() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(inputCheckIn));
        inputCheckIn.click();
    }

    private void openCheckOutCalendar() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(inputCheckOut));
        inputCheckOut.click();
    }

    public void setTodayCheckInDate() {
        openCheckInCalendar();
    }

    public void setTodayCheckOutDate() {
        openCheckOutCalendar();
    }

    public void setCheckInDate(LocalDate date) {
        openCheckInCalendar();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        System.out.println(date.format(formatter));
        WebElement checkInDatePicker = driver
                .findElement(By.xpath(String.format("//div[@aria-label='%s']", date.format(formatter))));
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(checkInDatePicker));
        System.out.println(checkInDatePicker.isDisplayed());
        checkInDatePicker.click();
    }

    public void setCheckOutDate(LocalDate date) {
        if (!tableCheckOut.isDisplayed()) {
            openCheckOutCalendar();
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        System.out.println(date.format(formatter));
        WebElement checkOutDatePicker = driver
                .findElement(By.xpath(String.format("//div[@aria-label='%s']", date.format(formatter))));
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(checkOutDatePicker));
        System.out.println(checkOutDatePicker.isDisplayed());
        checkOutDatePicker.click();
    }

    public String getCountNights() {
        return countNights.getAttribute("value");
    }

    public void clickButtonSearchHotels() {
        butSearchHotels.click();
    }

    public String getError() {
        return error.getText();
    }
}
