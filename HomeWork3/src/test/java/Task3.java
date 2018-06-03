import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Task3 {

    WebDriver driver;

    private void task3(){
        driver.get("http://localhost/litecart");
        ProductDetails productDetailsFromMainPage = getFirstProductDetailsMainPage();
        WebElement firstProduct = driver.findElement(By.cssSelector("#box-campaigns .content .link:nth-of-type(1)"));
        firstProduct.click();
        ProductDetails productDetailsFromItemPage = getFirstProductDetailsItemPage();

        Assert.assertEquals(productDetailsFromMainPage.name, productDetailsFromItemPage.name);
        Assert.assertEquals(productDetailsFromMainPage.regularPrice, productDetailsFromItemPage.regularPrice);
        Assert.assertEquals(productDetailsFromMainPage.discountPrice, productDetailsFromItemPage.discountPrice);

        Assert.assertTrue(productDetailsFromItemPage.regularPriceColor.contains("(102, 102, 102"));
        //Assert.assertEquals(productDetailsFromItemPage.regularPriceStrike, "line-through");
        Assert.assertEquals(productDetailsFromItemPage.regularPriceStrikeTag, "s");
        Assert.assertTrue(productDetailsFromItemPage.discountPriceColor.contains("(204, 0, 0"));
        Assert.assertEquals(productDetailsFromItemPage.discountPriceWeight, "700");


        Assert.assertTrue(productDetailsFromMainPage.regularPriceColor.contains("(119, 119, 119"));
        //Assert.assertEquals(productDetailsFromMainPage.regularPriceStrike, "line-through");
        Assert.assertTrue(productDetailsFromMainPage.discountPriceColor.contains("(204, 0, 0"));
        Assert.assertEquals(productDetailsFromMainPage.discountPriceWeight, "700");

    }


    @Test
    public void task3ChromeBrowser()
    {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        task3();
    }

    @Test
    public  void task3FirefoxBrowser()
    {
        FirefoxDriverManager.getInstance().setup();
        driver = new FirefoxDriver();
        task3();
    }

    @Test
    public void task3IEBrowser()
    {
        InternetExplorerDriverManager.getInstance().setup();
        driver = new InternetExplorerDriver();
        task3();
    }

    @After
    public void clean()
    {
        driver.quit();
    }

    public ProductDetails getFirstProductDetailsMainPage()
    {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        ProductDetails productDetails = new ProductDetails();

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("#box-campaigns .content li")));
        WebElement firstProduct = driver.findElement(By.cssSelector("#box-campaigns .content .link:nth-of-type(1)"));

        productDetails.name = firstProduct.findElement(By.cssSelector(".name")).getText();
        productDetails.regularPrice = firstProduct.findElement(By.cssSelector(".regular-price")).getText();
        productDetails.regularPriceColor = firstProduct.findElement(By.cssSelector(".regular-price")).getCssValue("color");
        productDetails.regularPriceStrike = firstProduct.findElement(By.cssSelector(".regular-price")).getCssValue("text-decoration-line");
        productDetails.regularPriceStrikeTag = firstProduct.findElement(By.cssSelector(".regular-price")).getTagName();

        productDetails.discountPrice = firstProduct.findElement(By.cssSelector(".campaign-price")).getText();
        productDetails.discountPriceColor = firstProduct.findElement(By.cssSelector(".campaign-price")).getCssValue("color");
        productDetails.discountPriceWeight = firstProduct.findElement(By.cssSelector(".campaign-price")).getCssValue("font-weight");

        return productDetails;
    }

    public ProductDetails getFirstProductDetailsItemPage()
    {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        ProductDetails productDetails = new ProductDetails();

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("#box-product")));

        productDetails.name = driver.findElement(By.cssSelector("#box-product .title")).getText();
        productDetails.regularPrice = driver.findElement(By.cssSelector("#box-product .regular-price")).getText();
        productDetails.regularPriceColor = driver.findElement(By.cssSelector("#box-product .regular-price")).getCssValue("color");
        productDetails.regularPriceStrike = driver.findElement(By.cssSelector("#box-product .regular-price")).getCssValue("text-decoration-line:");
        System.out.println(productDetails.regularPriceStrike);

        productDetails.discountPrice = driver.findElement(By.cssSelector("#box-product .campaign-price")).getText();
        productDetails.discountPriceColor = driver.findElement(By.cssSelector("#box-product .campaign-price")).getCssValue("color");
        productDetails.discountPriceWeight = driver.findElement(By.cssSelector("#box-product .campaign-price")).getCssValue("font-weight");

        return productDetails;
    }
}
