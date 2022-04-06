package tqs.tests;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import tqs.webpages.ConfirmationPage;
import tqs.webpages.HomePage;
import tqs.webpages.PurchasePage;
import tqs.webpages.ReservePage;

public class WebpagesTest {
    
    WebDriver driver;

    @Before
    public void setup(){
        //use FF Driver
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testFlightPurchase(){
        HomePage home = new HomePage(driver);
        home.selectDeparture("San Diego");
        home.selectDestination("Cairo");
        home.clickOnButton();

        ReservePage reserve = new ReservePage(driver);
        reserve.chooseFlight();

        PurchasePage purchase = new PurchasePage(driver);
        assertTrue(purchase.checkPage("Your flight from TLV to SFO has been reserved"));
        purchase.fillForm();

        ConfirmationPage confirmation = new ConfirmationPage(driver);
        assertTrue(confirmation.checkPage("Thank you for your purchase today!"));
    }

}
