import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task7 {

    MainPage mainPage;
    ProductPage productPage;
    CartPage cartPage;
    WebDriver driver;

    @Before
    public void beforeTest()
    {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
    }

    @Test
    public void task7()
    {
        mainPage.open();
        mainPage.openFirstProduct();
        int count = productPage.addProductToCart();
        if(count<4) {
            for(int i = count; i<4; i ++) {
                mainPage.open();
                mainPage.openFirstProduct();
                productPage.addProductToCart();
            }
        }
        mainPage.openCart();
        Assert.assertTrue(cartPage.deleteAllItemsFromCart().contains("There are no items in your cart."));
    }

    @After
    public void clean()
    {
        cartPage.closeBrowser();
    }
}
