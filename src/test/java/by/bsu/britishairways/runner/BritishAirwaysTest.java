package by.bsu.britishairways.runner;

import java.time.LocalDate;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import by.bsu.britishairways.pages.PageCars;
import by.bsu.britishairways.pages.PageFlights;
import by.bsu.britishairways.pages.PageHotels;

public class BritishAirwaysTest {
    WebDriver driver;

    @Test
    public void checkIfHideReturnDateCalendarAfterOneWayChoice() {
        PageFlights pageFlights = new PageFlights(driver);
        pageFlights.clickButtonContinue();
        pageFlights.openInputForm();
        pageFlights.clickRadioButtonOneWay();
        Assert.assertFalse(pageFlights.inputReturnDateIsEnabled());
    }

    @Test
    public void checkIfSelectReturnToDifferentLocationFieldNamePlaceAppear() {
        PageCars pageCars = new PageCars(driver);
        pageCars.clickButtonContinue();
        pageCars.openInputForm();
        pageCars.clickRadioButtonCars();
        pageCars.clickCheckBoxReturning();
    }

    @Test
    public void checkInMoreThanNinePassengers() {
        PageFlights pageFlights = new PageFlights(driver);
        pageFlights.clickButtonContinue();
        pageFlights.openInputForm();
        pageFlights.setInputTo("Moscow, Russia, DME, Domodedovo");
        pageFlights.setInputAdults("5");
        pageFlights.setInputTeens("4");
        pageFlights.setInputChildren("3");
    }

    @Test
    public void dataInFieldToAndInFieldFromIsTheSame() {
        PageFlights pageFlights = new PageFlights(driver);
        pageFlights.clickButtonContinue();
        pageFlights.openInputForm();
        pageFlights.setInputTo("New York, USA, NYC, New York (All Airports)");
        pageFlights.clickRadioButtonOneWay();
    }

    @Test
    public void findTicketWhenFieldToIsEmpty() {
        PageFlights pageFlights = new PageFlights(driver);
        pageFlights.clickButtonContinue();
        pageFlights.openInputForm();
        pageFlights.clearInputTo();
        pageFlights.clickRadioButtonOneWay();
    }

    @Test
    public void checkPerAdultCanChooseOneBaby() {
        PageFlights pageFlights = new PageFlights(driver);
        pageFlights.clickButtonContinue();
        pageFlights.openInputForm();
        pageFlights.setInputTo("Moscow, Russia, DME, Domodedovo");
        pageFlights.clickRadioButtonOneWay();
        pageFlights.setInputAdults("1");
        pageFlights.setInputInfants("2");
    }

    @Test
    public void returnDateCannotBeEarlierThanDepartureDate() {
        PageFlights pageFlights = new PageFlights(driver);
        pageFlights.clickButtonContinue();
        pageFlights.openInputForm();
        pageFlights.setDepartureDate(LocalDate.now().plusDays(2L));
        Assert.assertFalse(pageFlights.isReturnDateCanBePicked(LocalDate.now().plusDays(1L)));
    }

    @Test
    public void checkBabiesNoMoreThanAdults() {
        PageHotels pageHotels = new PageHotels(driver);
        pageHotels.clickButtonContinue();
        pageHotels.openInputForm();
        pageHotels.clickRadioButtonHotels();
        pageHotels.setInputAdultsInRoom("3");
        Assert.assertEquals(pageHotels.foundCountInputInfantsInRoom(), 4);
    }

    @Test
    public void childMustBeAge() {
        PageHotels pageHotels = new PageHotels(driver);
        pageHotels.clickButtonContinue();
        pageHotels.openInputForm();
        pageHotels.clickRadioButtonHotels();
        pageHotels.setInputDestination("Moscow, Russia, DME, Domodedovo");
        pageHotels.setInputChildrenInRoom("1");
        pageHotels.setTodayCheckInDate();
        pageHotels.setTodayCheckOutDate();
    }

    @Test
    public void departureDateCanBeEarlierThanReturnDate() {
        PageFlights pageFlights = new PageFlights(driver);
        pageFlights.clickButtonContinue();
        pageFlights.openInputForm();
        pageFlights.setDepartureDate(LocalDate.now().plusDays(1L));
        Assert.assertFalse(pageFlights.isReturnDateCanBePicked(LocalDate.now().plusDays(2L)));
    }

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36");
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
