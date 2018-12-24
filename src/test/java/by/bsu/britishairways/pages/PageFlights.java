package by.bsu.britishairways.pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageFlights extends Page {
    @FindBy(css = ".autoWrapper>input")
    private WebElement inputTo;

    @FindBy(xpath = "//label[@for='journeyTypeOWflights']")
    private WebElement radioButtonOneWay;

    @FindBy(css = ".styledSelect>#ad")
    private WebElement inputAdults;

    @FindBy(css = ".styledSelect>#ya")
    private WebElement inputTeens;

    @FindBy(css = ".styledSelect>#ch")
    private WebElement inputChildren;

    @FindBy(css = ".styledSelect>#inf")
    private WebElement inputInfants;

    @FindBy(css = "#flightSearchButton")
    private WebElement butSearch;

    @FindBy(id = "retDate")
    private WebElement inputReturnDate;

    @FindBy(id = "depDate")
    private WebElement inputDepDate;

    @FindBy(css = "#depDate_table>tbody")
    private WebElement tableDep;

    @FindBy(css = "#retDate_table>tbody")
    private WebElement tableRet;

    @FindBy(css = ".errorList>li")
    private WebElement error;

    public PageFlights(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clearInputTo() {
        inputTo.clear();
    }

    public void setInputTo(String place) {
        clearInputTo();
        inputTo.sendKeys(place);
    }

    public void setInputAdults(String number) {
        new Select(inputAdults).selectByValue(number);
    }

    public void setInputTeens(String number) {
        new Select(inputTeens).selectByValue(number);
    }

    public void setInputChildren(String number) {
        new Select(inputChildren).selectByValue(number);
    }

    public void setInputInfants(String number) {
        new Select(inputInfants).selectByValue(number);
    }

    public void clickRadioButtonOneWay() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(radioButtonOneWay));
        radioButtonOneWay.click();
    }

    private void openReturnDateInput() {
        inputReturnDate.click();
    }

    private void openDepartureDateInput() {
        inputDepDate.click();
    }

    public void setReturnDate(LocalDate date) {
        openReturnDateInput();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        driver.findElement(By.xpath("//div[@aria-label='" + date.format(formatter) + "']")).click();
    }

    public boolean isReturnDateCanBePicked(LocalDate date) {
        openReturnDateInput();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        return driver.findElement(By.xpath("//div[@aria-label='" + date.format(formatter) + "']")).getAttribute("class")
                .contains("picker__day--disabled");
    }

    public void setDepartureDate(LocalDate date) {
        openDepartureDateInput();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        driver.findElement(By.xpath("//div[@aria-label='" + date.format(formatter) + "']")).click();
    }

    public boolean isDepartureDateCanBeClicked(LocalDate date) {
        openDepartureDateInput();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        return driver.findElement(By.xpath("//div[@aria-label='" + date.format(formatter) + "']")).getAttribute("class")
                .contains("picker__day--disabled");
    }

    public boolean inputReturnDateIsEnabled() {
        return inputReturnDate.isEnabled();
    }

    public String getDateDep() {
        return inputDepDate.getText();
    }

    public String getDateReturn() {
        return inputReturnDate.getText();
    }

    public void clickButtonSearch() {
        butSearch.click();
    }

    public String getError() {
        return error.getText();
    }
}
