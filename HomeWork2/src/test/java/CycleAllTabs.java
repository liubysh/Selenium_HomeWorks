import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class CycleAllTabs {

    WebDriver driver;

    @Before
    public void setup()
    {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        driver.get("http://localhost/litecart/admin");

        WebElement userName = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.name("login"));

        userName.sendKeys("admin");
        password.sendKeys("admin");
        loginButton.click();
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
                WebElement header = driver.findElement(By.cssSelector("h1"));
                Assert.assertTrue(!header.getText().isEmpty());
            }
        }
    }

    @After
    public void  cleanup()
    {
        driver.quit();
    }
}
