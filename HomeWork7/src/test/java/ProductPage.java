import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends PageBase {

    public ProductPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    By byCartCount = new By.ByCssSelector(".content .quantity");

    @FindBy(css=".content .quantity")
    WebElement cartCount;
    @FindBy(css="#box-product .title")
    WebElement productTitle;
    @FindBy(css="select[name=\"options[Size]\"][required=\"required\"]")
    WebElement sizeDropdownList;
    @FindBy(css="button[name=add_cart_product]")
    WebElement addToCart;
    @FindBy(css="a[href=\"http://localhost/litecart/en/checkout\"][class=\"content\"]")
    WebElement cart;

    public int addProductToCart() {
        WebDriverWait wait = new WebDriverWait(driver,15);
        int count = Integer.parseInt(cartCount.getText());

        if(productTitle.getText().contains("Yellow Duck"))
        {
            Select dropdownList = new Select(sizeDropdownList);
            dropdownList.selectByValue("Medium");
        }

        addToCart.click();

        wait.until(ExpectedConditions.textToBe(byCartCount, Integer.toString(++count)));
        return Integer.parseInt(cartCount.getText());
    }

    public void openCart() {
        cart.click();
    }
}
