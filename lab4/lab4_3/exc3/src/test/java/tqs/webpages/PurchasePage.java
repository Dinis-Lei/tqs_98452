package tqs.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PurchasePage {
    
    private WebDriver driver;

    //Page URL
    private static String PAGE_URL="https://blazedemo.com/purchase.php";

    @FindBy(tagName = "h2")
    private WebElement title;

    @FindBy(id = "inputName")
    private WebElement inptName;   
    @FindBy(id = "address")
    private WebElement inptAddress;    
    @FindBy(id = "city")
    private WebElement inptCity;    
    @FindBy(id = "state")
    private WebElement inptState;    
    @FindBy(id = "zipCode")
    private WebElement inptZipCode;    
    @FindBy(id = "cardType")
    private WebElement inptCardType;    
    @FindBy(id = "creditCardNumber")
    private WebElement inptCreditCardNumber;    
    @FindBy(id = "creditCardMonth")
    private WebElement inptMonth;    
    @FindBy(id = "creditCardYear")
    private WebElement inptYear;   
    @FindBy(id = "nameOnCard")
    private WebElement inptNameOnCard;  
    @FindBy(id = "rememberMe")
    private WebElement btnRememberMe;
    @FindBy(css = ".btn-primary")
    private WebElement btnPurchase;

    public PurchasePage(WebDriver driver){
        this.driver=driver;
        driver.get(PAGE_URL);
        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public boolean checkPage(String text){
        return title.isDisplayed() && title.getText().contains(text);
    }

    public void fillForm(){
        inptName.click();
        inptName.sendKeys("Bob");

        inptAddress.click();
        inptAddress.sendKeys("123 Mainer Street");

        inptCity.click();
        inptCity.sendKeys("Alltown");

        inptState.click();
        inptState.sendKeys("Stateless");

        inptZipCode.click();
        inptZipCode.sendKeys("54321");

        inptCardType.click();
        Select option = new Select(inptCardType); 
        option.selectByIndex(1);

        inptCreditCardNumber.click();
        inptCreditCardNumber.sendKeys("123456789");

        inptMonth.click();
        inptMonth.sendKeys("12");

        inptYear.click();
        inptYear.sendKeys("2022");

        inptNameOnCard.click();
        inptNameOnCard.sendKeys("Bob the Bobbers");

        btnRememberMe.click();

        btnPurchase.click();

    }

}
