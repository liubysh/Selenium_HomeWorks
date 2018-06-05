import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Task05 {

    WebDriver driver;

    public Task05()
    {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
    }

    @Before
    public void setup()
    {
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @After
    public void  cleanup()
    {
        driver.quit();
    }

    @Test
    public void Task05()
    {
        openCountryPage(driver);
        openAustriaPage(driver);
        openAllLinksOnAustriaPage(driver);
    }

    public void openCountryPage(WebDriver driver)
    {
        driver.findElement(By.cssSelector("a[href=\"http://localhost/litecart/admin/?app=countries&doc=countries\"]")).click();
    }

    public void openAustriaPage(WebDriver driver)
    {
        driver.findElement(By.cssSelector("a[href=\"http://localhost/litecart/admin/?app=countries&doc=edit_country&country_code=AT\"]")).click();
    }

    public void openAllLinksOnAustriaPage(WebDriver driver)
    {
        By listOfLinksSelector = By.cssSelector("#content td a[href*=wikipedia]");
        List<WebElement> listOfLinks = driver.findElements(listOfLinksSelector);

        for(int i = 0; i < listOfLinks.size(); i++)
        {
            openAndCloseNewWindow(driver, listOfLinks.get(i));
            listOfLinks = driver.findElements(listOfLinksSelector);
        }
    }

    public void openAndCloseNewWindow(WebDriver driver, WebElement element)
    {
        WebDriverWait wait = new WebDriverWait(driver, 5);

        element.click();
        //wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".mw-body")));
        Object[] listOfWindows = driver.getWindowHandles().toArray();
        String currentWindow = driver.getWindowHandle();

        for(int i = 0; i < listOfWindows.length; i++)
        {
            if(!listOfWindows[i].toString().equals(driver.getWindowHandle()))
            {
                driver.switchTo().window(listOfWindows[i].toString());
                driver.close();
                driver.switchTo().window(currentWindow);
                break;
            }
        }
    }
}
