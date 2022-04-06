package tqs.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage {
    
    private WebDriver driver;

    //Page URL
    private static String PAGE_URL="https://blazedemo.com/confirmation.php";

    @FindBy(tagName = "h1")
    private WebElement title;

    public ConfirmationPage(WebDriver driver){
        this.driver=driver;
        driver.get(PAGE_URL);
        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public boolean checkPage(String text){
        return title.isDisplayed() && title.getText().contains(text); 
    }


}
