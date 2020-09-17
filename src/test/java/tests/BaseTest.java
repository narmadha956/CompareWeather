package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;

    public BaseTest()
    {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-fullscreen");
        options.setExperimentalOption("excludeSwitches",
                Arrays.asList("disable-popup-blocking"));
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("download.default_directory", "./src/test/java/downloads");
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("disable-popup-blocking");
        options.addArguments("disable-notifications");
        driver =  new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://www.ndtv.com/");
        RestAssured.baseURI="http://api.openweathermap.org/";
    }

}
