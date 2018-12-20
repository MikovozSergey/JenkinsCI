package pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PageHotels {
    private WebDriver driver;

    public PageHotels(WebDriver driver) {
        this.driver = driver;
        driver.get("https://www.britishairways.com/en-us/flights-and-holidays/flights");
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "hotelRadioSelector")
    private WebElement radioButtonHotels;

    @FindBy(id = "planTripHotelDestination")
    private WebElement inputDestination;

    @FindBy(id = "hotelSearchButtonHS")
    private WebElement butSearchHotels;

    @FindBy(id = "checkInGO")
    private WebElement inputCheckIn;

    @FindBy(id = "checkOutGO")
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

    @FindBy(name = "Continue")
    private WebElement buttonContinue;

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

    public void clickInputCheckIn() {
        inputCheckIn.click();
    }

    public void clickInputCheckOut() {
        inputCheckOut.click();
    }

    public void clickDateCheckIn() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        List<WebElement> arrayCheckIn = tableCheckIn.findElements(By.cssSelector("td>div"));
        for (WebElement cell : arrayCheckIn) {
            if (cell.getAttribute("aria-label").equals(dateFormat.format(today))) {
                cell.click();
                break;
            }
        }
    }

    public void clickDateCheckOut(int count) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, count);
        Date afterTomorrow = calendar.getTime();
        List<WebElement> arrayCheckOut = tableCheckOut.findElements(By.cssSelector("td>div"));
        for (WebElement cell : arrayCheckOut) {
            if (cell.getAttribute("aria-label").equals(dateFormat.format(afterTomorrow))) {
                cell.click();
                break;
            }
        }
    }

    public String getCountNights() {
        return countNights.getAttribute("value");
    }

    public void clickButtonSearchHotels() {
        butSearchHotels.click();
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
