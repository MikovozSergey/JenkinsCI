package by.bsu.britishairways.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.PageCars;
import pages.PageFlights;
import pages.PageHotels;

public class BritishAirwaysTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36");
        options.addArguments("--start-maximized");
        driver = new ChromeDriver();
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void checkIfSelectReturnToDifferentLocationFieldNamePlaceAppear() {
        PageCars pageCars = new PageCars(driver);
        pageCars.clickRadioButtonCars();
        pageCars.clickCheckBoxReturning();
    }

    @Test
    public void checkInMoreThanNinePassengers() {
        PageFlights pageFlights = new PageFlights(driver);
        pageFlights.clearInputTo();
        pageFlights.setInputTo("Moscow, Russia, DME, Domodedovo");
        pageFlights.setInputAdults("5");
        pageFlights.setInputTeens("4");
        pageFlights.setInputChildren("3");
    }

    @Test
    public void dataInFieldToAndInFieldFromIsTheSame() {
        PageFlights pageFlights = new PageFlights(driver);
        pageFlights.clearInputTo();
        pageFlights.setInputTo("New York, USA, NYC, New York (All Airports)");
        pageFlights.clickRadioButtonOneWay();
    }

    @Test
    public void checkIfHideReturnDateCalendarAfterOneWayChoice() {
        PageFlights pageFlights = new PageFlights(driver);
        pageFlights.clickRadioButtonOneWay();
        Assert.assertFalse(pageFlights.inputReturnDateIsEnabled());
    }

    @Test
    public void findTicketWhenFieldToIsEmpty() {
        PageFlights pageFlights = new PageFlights(driver);
        pageFlights.clearInputTo();
        pageFlights.clickRadioButtonOneWay();
    }

    @Test
    public void checkPerAdultCanChooseOneBaby() {
        PageFlights pageFlights = new PageFlights(driver);
        pageFlights.setInputTo("Moscow, Russia, DME, Domodedovo");
        pageFlights.clickRadioButtonOneWay();
        pageFlights.setInputAdults("1");
        pageFlights.setInputInfants("2");
    }

    @Test
    public void returnDateCannotBeEarlierThanDepartureDate() {
        PageFlights pageFlights = new PageFlights(driver);
        pageFlights.clickInputReturnDate();
        pageFlights.clickDateReturn();
        pageFlights.clickInputDepDate();
        pageFlights.clickDateDep(2);
        Assert.assertEquals(pageFlights.getDateDep(), pageFlights.getDateReturn());
    }

    @Test
    public void checkBabiesNoMoreThanAdults() {
        PageHotels pageHotels = new PageHotels(driver);
        pageHotels.clickButtonContinue();
        pageHotels.clickRadioButtonHotels();
        pageHotels.setInputAdultsInRoom("3");
        Assert.assertEquals(pageHotels.foundCountInputInfantsInRoom(), 4);
    }

    @Test
    public void childMustBeAge() {
        PageHotels pageHotels = new PageHotels(driver);
        pageHotels.clickRadioButtonHotels();
        pageHotels.setInputDestination("Moscow, Russia, DME, Domodedovo");
        pageHotels.setInputChildrenInRoom("1");
        pageHotels.clickInputCheckIn();
        pageHotels.clickDateCheckIn();
        pageHotels.clickInputCheckOut();
        pageHotels.clickDateCheckOut(1);
    }

    @Test
    public void whenSelectTheDateOfCheckInHotelAndEvictionFromHotelChangesNumberOfNights() {
        PageHotels pageHotels = new PageHotels(driver);
        pageHotels.clickRadioButtonHotels();
        pageHotels.clickInputCheckIn();
        pageHotels.clickDateCheckIn();
        pageHotels.clickInputCheckOut();
        pageHotels.clickDateCheckOut(2);
        Assert.assertEquals(pageHotels.getCountNights(), "2");
    }
}
