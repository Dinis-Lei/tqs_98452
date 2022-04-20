package tqs.api;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

class WebPageSteps {
    
    private final WebDriver driver = new FirefoxDriver();

    @Given("I'm on {string}")
    void goToPage(String url){
        driver.get(url);
    }

    @When("I write on country input: {string}")
    void writeCountry(String country){
        
    }

    @When("select {int} days")
    void selectNDays(int nDays){

    }

    @When("click on search")
    void pressButton(){

    }

    @Then("I should see a table with data for <country> of the last <n> days")
    void result(String country, int nDays){
        
    }

}
