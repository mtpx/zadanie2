
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;


public class homePage {
    WebDriver driver;
    WebDriverWait wait;
    public homePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver,15);
    }

    //lokalizatory elementow


    @FindBy (xpath="//ul[@id='homefeatured']//div[@class='product-container']") List<WebElement> items;
    @FindBy (css=".product-name") List<WebElement> itemsNames;
    @FindBy (css=".product_img_link") List<WebElement> itemsPhotos;
    @FindBy (xpath="//a[@title='Add to cart']") List<WebElement> addToCartButtons;
    @FindBy (xpath="//a[@title='Proceed to checkout']") WebElement proceedToCheckout;
    @FindBy (xpath="//h2") WebElement productAddedToBasketConfirmation;
    @FindBy (css="#layer_cart_product_title") WebElement productNameOnConfirmation;



    //metody




    public String clickOnRandomItem(){
        Actions action = new Actions(driver);

        //wyliczanie ilosci dostepnych produktow
        int numberOfItems =items.size()-1;
        System.out.println("ilosc dostepnych produktow: "+numberOfItems);

        //losowe wybieranie liczby z zakresu od 0 do ilości dostępnych produktów
        int randomItemNumber = new Random().nextInt(numberOfItems);
        System.out.println("numer losowo wybranego produktu: "+randomItemNumber);
        System.out.println("nazwa losowo wybranego produktu: "+itemsNames.get(randomItemNumber+1).getText());
        String nameOfActualItem = itemsNames.get(randomItemNumber+1).getText(); //przpisanie wybranej nazwy produktu do zmiennej w celu jej zwrocenia i porownania w asercji w klasie testowej

        //przesuwanie kursora nad losowo wybrany produkt
        action.moveToElement(itemsPhotos.get(randomItemNumber)).build().perform();

        //klikniecie 'add to cart' przy wybranym produkcie
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButtons.get(randomItemNumber)));
        addToCartButtons.get(randomItemNumber).click();
        wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckout));
        return nameOfActualItem;
    }
    public void clickProceedToCheckOut(){
        proceedToCheckout.click();
    }

    public String getProductAddedToBasketConfirmation(){
        return productAddedToBasketConfirmation.getText();
    }

    public String getProductNameOnConfirmation(){
        return productNameOnConfirmation.getText();
    }

}
