import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.List;

public class CycleAllTabs {

    EventFiringWebDriver driver;

    @Before
    public void setup()
    {
        ChromeDriverManager.getInstance().setup();
        driver = new EventFiringWebDriver(new ChromeDriver());
        driver.register(new Listener());
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @Test
    public void cycleAllTabs()
    {
        List<WebElement> tabsList = driver.findElements(By.cssSelector("ul#box-apps-menu li#app-"));
        for (int i = 0; i< tabsList.size(); i++)
        {
            List<WebElement> innerTabsList;
            WebElement tab;
            innerTabsList = driver.findElements(By.cssSelector("ul#box-apps-menu li#app-"));
            tab = innerTabsList.get(i);
            tab.click();
            WebElement header = driver.findElement(By.cssSelector("h1"));
            Assert.assertTrue(!header.getText().isEmpty());

            innerTabsList = driver.findElements(By.cssSelector("ul#box-apps-menu li#app-"));
            tab = innerTabsList.get(i);
            List<WebElement> subTabs;
            subTabs = tab.findElements(By.cssSelector("li"));

            for(int j = 0; j < subTabs.size(); j++)
            {
                innerTabsList = driver.findElements(By.cssSelector("ul#box-apps-menu li#app-"));
                tab = innerTabsList.get(i);
                subTabs = tab.findElements(By.cssSelector("li"));
                WebElement subtab = subTabs.get(j);
                subtab.click();
                Assert.assertTrue(driver.findElements(By.cssSelector("h1")).size()>0);
            }
        }
    }

    @After
    public void  cleanup()
    {
        driver.quit();
    }
}
