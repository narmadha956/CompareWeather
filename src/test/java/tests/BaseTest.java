package tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BaseTest {
    protected ChromeDriver driver;

    public BaseTest()
    {
        System.setProperty("webdriver.chrome.driver", "./src/test/java/tracsweb/Drivers/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
         driver =  new ChromeDriver(chromeOptions);

    }

}
