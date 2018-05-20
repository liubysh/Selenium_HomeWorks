import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class FirstTest {

    WebDriver driver;

    @Test
    public void firstTest()
    {
        FirefoxDriverManager.getInstance().setup();
        driver = new FirefoxDriver();
        driver.get("https://www.google.com");
    }

    @Test
    public void secondTest()
    {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        driver.get("https://www.google.com");
    }

    @Test
    public void thirdTest()
    {
        InternetExplorerDriverManager.getInstance().setup();
        driver = new InternetExplorerDriver();
        driver.get("https://www.google.com");
    }

    @After
    public void cleanUp()
    {
        driver.quit();
    }
}
