import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Task4_2 {

    WebDriver driver;
    By byCartCount = new By.ByCssSelector(".content .quantity");

    public Task4_2()
    {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void task4_2()
    {
        openMainPage(driver);
        int count = openFirstProductAndAddtoCart(driver);
        if(count<4) {
            for(int i = count; i<4; i ++) {
                openMainPage(driver);
                openFirstProductAndAddtoCart(driver);
            }
        }
        openCart(driver);
        Assert.assertTrue(deleteAllItemsFromCart(driver).contains("There are no items in your cart."));
    }


    @After
    public void clean()
    {
        driver.quit();
    }

    public void openMainPage(WebDriver driver)
    {
        driver.get("http://localhost/litecart");
    }

    public int openFirstProductAndAddtoCart(WebDriver driver)
    {
        WebDriverWait wait = new WebDriverWait(driver,15);

        WebElement cartCount = driver.findElement(byCartCount);
        int count = Integer.parseInt(cartCount.getText());

        WebElement firstProduct = driver.findElement(By.cssSelector("#box-most-popular .content li:nth-of-type(1)"));
        firstProduct.click();

        if(driver.findElement(By.cssSelector("#box-product .title")).getText().contains("Yellow Duck"))
        {
            driver.findElement(By.cssSelector(".options select"));
            Select dropdownList = new Select(driver.findElement(By.cssSelector("select[name=\"options[Size]\"][required=\"required\"]")));
            dropdownList.selectByValue("Medium");
        }

        WebElement addToCart = driver.findElement(By.cssSelector("button[name=add_cart_product]"));
        addToCart.click();

        wait.until(ExpectedConditions.textToBe(byCartCount, Integer.toString(++count)));
        return Integer.parseInt(driver.findElement(byCartCount).getText());
    }

    public void openCart(WebDriver driver)
    {
        driver.findElement(By.cssSelector("a[href=\"http://localhost/litecart/en/checkout\"][class=\"content\"]")).click();
    }

    public String deleteAllItemsFromCart(WebDriver driver)
    {
        WebDriverWait wait = new WebDriverWait(driver, 5);


        if(driver.findElement(By.id("box-checkout-customer")).isDisplayed())
        {
            driver.findElement(By.cssSelector(".shortcuts .shortcut:nth-of-type(1)")).click();

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
        return driver.findElement(By.id("checkout-cart-wrapper")).getText();
    }

    private boolean isElementPresent(By elementBy)
    {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        boolean result = driver.findElements(elementBy).size()>0;
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return  result;
    }
}
