package tests;

import com.epam.reportportal.service.ReportPortal;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.event.Level;
import org.testng.annotations.AfterSuite;
import utilities.ConfigDetails;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;
    protected Map<String, String> defaultQueryParams = new HashMap<String, String>();
    protected static ConfigDetails.runner runner;


    @BeforeSuite
    public void initialiseTestCase()
    {
        runner  = ConfigDetails.configReader.fetchConfig();

        defaultQueryParams.put("appid", "7fe67bf08c80ded756e598d6f8fedaea");

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
        RestAssured.baseURI=(runner.getBase().getBaseAPI());
    }

    @DataProvider(name = "suite")
    public Iterator<String> data() {

        String[] cityNames = runner.getBase().getCityNames();
        return Arrays.asList(cityNames).iterator();

    }

    public boolean varianceLogic(String variance, String metric, String apiValue, String uiValue)
    {
        try{
        double apiMeasurement = Double.parseDouble(apiValue);
        double uiMeasurement = Double.parseDouble(uiValue);
        if(metric.equals("degree"))
            apiMeasurement = apiMeasurement - 273.15;
        int allowed = Integer.parseInt(variance);
        if(Math.abs(apiMeasurement-uiMeasurement)<=allowed)
            return true;
        }catch (NumberFormatException n)
        {
            n.printStackTrace();
            return false;
        }
        return false;
    }

    @AfterSuite
    public void tearDown()
    {
        driver.close();
        driver.quit();
    }

    public static synchronized void attachToRP(Level level, String response) {
        try {

            File file = new File(".//src//test//resources//Request.txt");
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            fw.write(response);
            fw.close();
            String fileName = "Failed_RequestResponse";
            ReportPortal.emitLog(fileName, level.toString(), new Date(), file);
            file.delete();

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
