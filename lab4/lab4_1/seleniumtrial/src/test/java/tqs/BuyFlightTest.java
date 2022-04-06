// Generated by Selenium IDE

package tqs;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.MatcherAssert.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.seljup.SeleniumJupiter;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;

@ExtendWith(SeleniumJupiter.class)
public class BuyFlightTest {
  // private WebDriver driver;
  // private Map<String, Object> vars;
  // JavascriptExecutor js;
  // @BeforeEach
  // public void setUp() {
  //   driver = new FirefoxDriver();
  //   js = (JavascriptExecutor) driver;
  //   vars = new HashMap<String, Object>();
  // }
  // @AfterEach
  // public void tearDown() {
  //   driver.quit();
  // }
  @Test
  public void buyFlight(FirefoxDriver driver) {
    driver.get("https://blazedemo.com/");
    driver.manage().window().setSize(new Dimension(550, 693));
    driver.findElement(By.cssSelector(".container:nth-child(3)")).click();
    driver.findElement(By.name("fromPort")).click();
    {
      WebElement dropdown = driver.findElement(By.name("fromPort"));
      dropdown.findElement(By.xpath("//option[. = 'San Diego']")).click();
    }
    driver.findElement(By.cssSelector(".form-inline:nth-child(1) > option:nth-child(5)")).click();
    driver.findElement(By.name("toPort")).click();
    {
      WebElement dropdown = driver.findElement(By.name("toPort"));
      dropdown.findElement(By.xpath("//option[. = 'Cairo']")).click();
    }
    driver.findElement(By.cssSelector(".form-inline:nth-child(4) > option:nth-child(7)")).click();
    driver.findElement(By.cssSelector(".btn-primary")).click();
    driver.findElement(By.cssSelector("tr:nth-child(5) .btn")).click();
    driver.findElement(By.id("inputName")).click();
    driver.findElement(By.id("inputName")).sendKeys("Bob");
    driver.findElement(By.id("address")).click();
    driver.findElement(By.id("address")).sendKeys("123 Mainer Street");
    driver.findElement(By.id("city")).click();
    driver.findElement(By.id("city")).sendKeys("Alltown");
    driver.findElement(By.id("state")).click();
    driver.findElement(By.id("state")).sendKeys("Stateless");
    driver.findElement(By.id("zipCode")).click();
    driver.findElement(By.id("zipCode")).sendKeys("54321");
    driver.findElement(By.id("cardType")).click();
    driver.findElement(By.cssSelector("option:nth-child(1)")).click();
    driver.findElement(By.id("creditCardNumber")).click();
    driver.findElement(By.id("creditCardNumber")).sendKeys("123456789");
    driver.findElement(By.id("creditCardMonth")).click();
    driver.findElement(By.id("creditCardMonth")).sendKeys("12");
    driver.findElement(By.id("creditCardYear")).click();
    driver.findElement(By.id("creditCardYear")).sendKeys("2022");
    driver.findElement(By.id("nameOnCard")).click();
    driver.findElement(By.id("nameOnCard")).sendKeys("Bob the Bobbers");
    driver.findElement(By.id("rememberMe")).click();
    driver.findElement(By.cssSelector(".btn-primary")).click();
    driver.findElement(By.cssSelector(".hero-unit")).click();
    driver.findElement(By.cssSelector(".hero-unit")).click();
    assertThat(driver.getTitle(), is("BlazeDemo Confirmation"));
  }
}