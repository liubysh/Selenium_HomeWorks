import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.print.DocFlavor;
import java.io.File;
import java.net.URI;
import java.net.URL;

public class Task4 {
    WebDriver driver;
    String productName = "Ol Product name1";
    String productCode = "Ol Product code1";
    String productPriceUSD = "26";
    String productPriceEUR = "30";

    @Test
    public void Test4()
    {
        setupMethod();
        openCatalogPage();
        addNewProduct();
        Assert.assertTrue(driver.findElement(By.linkText(productName)).isDisplayed());
    }

    @After
    public void clean()
    {
        driver.quit();
    }

    public void setupMethod()
    {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    public void openCatalogPage()
    {
        driver.findElement(By.cssSelector("#box-apps-menu li:nth-of-type(2)")).click();
    }

    public void addNewProduct()
    {
        driver.findElement(By.cssSelector("#content .button:nth-of-type(2)")).click();
        driver.findElement(By.name("name[en]")).sendKeys(productName);
        driver.findElement(By.name("code")).sendKeys(productCode);
        driver.findElement(By.cssSelector("input[type=\"checkbox\"][value=\"1-3\"]")).click();

        Select soldOutStatusList = new Select(driver.findElement(By.name("sold_out_status_id")));
        soldOutStatusList.selectByValue("2");

//        URL path = getClass().getClassLoader().getResource("test/image.png");
//        File file = new File(path.getFile());
        driver.findElement(By.name("new_images[]")).sendKeys("C:\\Users\\Ruslan\\IdeaProjects\\Task4\\src\\test\\image.png");

        driver.findElement(By.linkText("Prices")).click();
        driver.findElement(By.name("prices[USD]")).sendKeys(productPriceUSD);
        driver.findElement(By.name("prices[EUR]")).sendKeys(productPriceEUR);
        driver.findElement(By.name("save")).click();
    }
}
