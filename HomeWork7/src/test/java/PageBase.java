import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PageBase {

    protected WebDriver driver;

    public PageBase(WebDriver driver)
    {
        this.driver = driver;
    }

    public void closeBrowser()
    {
        driver.quit();
    }
}
