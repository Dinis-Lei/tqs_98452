package tqs.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReservePage {
    
    private WebDriver driver;

    //Page URL
    private static String PAGE_URL="https://blazedemo.com/reserve.php";

    @FindBy(css = "tr:nth-child(5) .btn")
    private WebElement button;

    public ReservePage(WebDriver driver){
        this.driver=driver;
        driver.get(PAGE_URL);
        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public void chooseFlight(){
        button.click();
    }

}
