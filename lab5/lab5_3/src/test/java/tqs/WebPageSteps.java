package tqs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class WebPageSteps {
    
    private final WebDriver driver = new FirefoxDriver();

    @When("I go to {string}")
    public void i_go_to(String url){
        driver.get(url);
    }

    @When("select {string} and {string}")
    public void select_places(String departure, String destination){
        driver.findElement(By.name("fromPort")).click();
        {
          WebElement dropdown = driver.findElement(By.name("fromPort"));
          dropdown.findElement(By.xpath("//option[. = '"+ departure +"']")).click();
        }

        driver.findElement(By.name("toPort")).click();
        {
          WebElement dropdown = driver.findElement(By.name("toPort"));
          dropdown.findElement(By.xpath("//option[. = '"+ destination +"']")).click();
        }
    }

    @When("click on the button")
    public void click_on_the_button() {
        driver.findElement(By.cssSelector(".btn-primary")).click();
    }

    @When("select {int} th button")
    public void select_nth_button(Integer int1) {
        driver.findElement(By.cssSelector("tr:nth-child("+ int1 +") .btn")).click();
    }

    @When("fill the form with name {string}, address {string}, city {string}, state {string}, zip code {int}, card type {string}, credit card number {int}, month {int}, year {int}, name on card {string}")
    public void fill_the_form(String string, String string2, String string3,
      String string4, Integer int1, String string5, 
      Integer int2, Integer int3, Integer int4, String string6) {
        driver.findElement(By.id("inputName")).click();
        driver.findElement(By.id("inputName")).sendKeys(string);
        driver.findElement(By.id("address")).click();
        driver.findElement(By.id("address")).sendKeys(string2);
        driver.findElement(By.id("city")).click();
        driver.findElement(By.id("city")).sendKeys(string3);
        driver.findElement(By.id("state")).click();
        driver.findElement(By.id("state")).sendKeys(string4);
        driver.findElement(By.id("zipCode")).click();
        driver.findElement(By.id("zipCode")).sendKeys(""+int1);
        driver.findElement(By.id("cardType")).click();
        driver.findElement(By.cssSelector("option:nth-child(1)")).click();
        driver.findElement(By.id("creditCardNumber")).click();
        driver.findElement(By.id("creditCardNumber")).sendKeys(""+int2);
        driver.findElement(By.id("creditCardMonth")).click();
        driver.findElement(By.id("creditCardMonth")).sendKeys(""+int3);
        driver.findElement(By.id("creditCardYear")).click();
        driver.findElement(By.id("creditCardYear")).sendKeys(""+int4);
        driver.findElement(By.id("nameOnCard")).click();
        driver.findElement(By.id("nameOnCard")).sendKeys(string6);
    }
    @When("click Purchase flight")
    public void click_purchase_flight() {
        driver.findElement(By.cssSelector(".btn-primary")).click();
    }



    @Then("I should be redirected to {string}")
    public void i_should_be_redirected_to(String url) {
        assertEquals(url, driver.getCurrentUrl());
    }


}
