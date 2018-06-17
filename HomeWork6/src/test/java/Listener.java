import org.openqa.selenium.*;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Listener extends AbstractWebDriverEventListener {

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver)
    {
        System.out.println("Start search for: " + by);
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver)
    {
        System.out.println(by + " has been found");
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver)
    {
        System.out.println("Exception has been found: " + throwable.getMessage());
        File tempFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try
        {
            Files.copy(tempFile.toPath(), new File(System.currentTimeMillis() + "screenshot.png").toPath());
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
