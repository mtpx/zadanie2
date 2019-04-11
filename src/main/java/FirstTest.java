import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class FirstTest {

    //-----------------------------------Global Variables-----------------------------------
    public WebDriver driver;

    public String testURL = " http://automationpractice.com/index.php";

    //-----------------------------------Test Setup-----------------------------------
    @BeforeMethod
    public void setupTest (){
        //Create a new ChromeDriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
        driver  = new ChromeDriver(options);
        driver.navigate().to(testURL);
    }

    //-----------------------------------Tests-----------------------------------
    @Test
    public void firstTest () {
        homePage objHomePage = new homePage(driver);

        String productName =objHomePage.clickOnRandomItem();

        assertEquals(objHomePage.getProductAddedToBasketConfirmation(),"Product successfully added to your shopping cart");  //asercja informacji o prawidlowym dodaniu produktu do koszyka
        assertEquals(objHomePage.getProductNameOnConfirmation(), productName);  //asercja nazwy przed i po dodaniu do koszyka

        objHomePage.clickProceedToCheckOut();

    }

    //-----------------------------------Test TearDown-----------------------------------
    @AfterMethod
    public void teardownTest (){
        driver.quit();
    }
}