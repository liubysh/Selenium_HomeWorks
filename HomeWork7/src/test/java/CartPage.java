import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CartPage extends PageBase {

    public  CartPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".shortcuts .shortcut:nth-of-type(1)")
    WebElement firstProduct;
    @FindBy(id = "checkout-cart-wrapper")
    WebElement emptyCartText;

    public String deleteAllItemsFromCart()
    {
        WebDriverWait wait = new WebDriverWait(driver, 5);

        if(driver.findElement(By.id("box-checkout-customer")).isDisplayed())
        {
            firstProduct.click();

            By removeCartItemBy = By.name("remove_cart_item");
            By tableRow = By.cssSelector("table.dataTable.rounded-corners tr:nth-of-type(1)");

            do {
                WebElement tableRowElement = driver.findElement(tableRow);
                driver.findElement(removeCartItemBy).click();
                wait.until(ExpectedConditions.stalenessOf(tableRowElement));
            }
            while (isElementPresent(removeCartItemBy));
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return emptyCartText.getText();
    }

    private boolean isElementPresent(By elementBy)
    {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        boolean result = driver.findElements(elementBy).size()>0;
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return  result;
    }
}
