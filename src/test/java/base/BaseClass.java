package base;

import com.edfa3ly.CartPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class BaseClass
{
    private WebDriver driver;
    protected CartPage cartPage;
    @BeforeMethod
    public void setup()
    {
        System.setProperty("webdriver.chrome.driver", "Resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.edfa3ly.com/cart");
        cartPage = new CartPage(driver);

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
