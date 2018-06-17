import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends PageBase {

    public  MainPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#box-most-popular .content li:nth-of-type(1)")
    WebElement firstProduct;
    @FindBy(css="a[href=\"http://localhost/litecart/en/checkout\"][class=\"content\"]")
    WebElement cart;

    public void open()
    {
        driver.get("http://localhost/litecart");
    }

    public void openFirstProduct()
    {
        firstProduct.click();
    }

    public void openCart()
    {
        cart.click();
    }
}
