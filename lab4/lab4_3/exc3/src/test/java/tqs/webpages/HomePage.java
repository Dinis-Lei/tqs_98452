package tqs.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    
    private WebDriver driver;

    //Page URL
    private static String PAGE_URL="https://blazedemo.com/";

    @FindBy(name="fromPort")
    private WebElement fromPort;

    @FindBy(name="toPort")
    private WebElement toPort;

    @FindBy(css = ".btn-primary")
    private WebElement button;


    public HomePage(WebDriver driver){
        this.driver=driver;
        driver.get(PAGE_URL);
        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public void clickOnButton(){
        button.click();
    }

    public void selectDeparture(String option){
        fromPort.click();
        fromPort.findElement(By.xpath("//option[. = '"+ option +"']")).click();
    }

    public void selectDestination(String option){
        toPort.click();
        toPort.findElement(By.xpath("//option[. = '"+ option +"']")).click();
    }

}
