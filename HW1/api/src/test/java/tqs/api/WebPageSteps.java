 package tqs.api;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.concurrent.TimeUnit;

public class WebPageSteps {
    
    private final WebDriver driver = new FirefoxDriver();

    @Before
    public void setUp(){
        driver.manage().timeouts();
    }

     @After
    public void tearDown() {
      driver.quit();
    }
 
     @Given("I'm on {string}")
     public void goToPage(String url){
         driver.get(url);
     }
 
     @When("I write on country input: {string}")
     public void writeCountry(String country){
         driver.findElement(By.cssSelector(".form-control")).click();
         driver.findElement(By.cssSelector(".form-control")).sendKeys(country);
     }
 
     @When("select {int} days")
     public void selectNDays(int nDays){
         driver.findElement(By.cssSelector(".col-2")).click();
         driver.findElement(By.cssSelector(".col-2")).sendKeys(""+nDays);
     }
 
     @When("click on search")
     public void pressButton(){
         driver.findElement(By.id("search-btn")).click();
     }
 
     @Then("I should see a table with data for {string} of the last {int} days")
     public void result(String country, int nDays) throws InterruptedException{
        TimeUnit.SECONDS.sleep(5);
        WebElement countryTitle = driver.findElement(By.id("countryTitle"));
        assertThat(countryTitle.getText()).isEqualTo(country);
        for (int i = 0; i < nDays; i++){
            List<WebElement> elements = driver.findElements(By.id("day"+i));
            assert(elements.size() > 0);
        }
         
     }

 }