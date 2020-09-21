package Handler;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.MapPage;

import java.io.File;

public class MapAction {
    WebDriver driver;
    private MapPage mapPageObjects;

    public MapAction(WebDriver driver) {
        mapPageObjects = new MapPage(driver);
        this.driver = driver;
    }

    public void searchCity(String city) {
        MapPage map = new MapPage(driver);
        map.citySearch.click();
        map.citySearch.clear();
        map.citySearch.sendKeys(city);
        String isChecked = driver.findElement(By.id(city)).getAttribute("checked");
        if (!isChecked.equalsIgnoreCase("true"))
            driver.findElement(By.id(city)).click();
    }

    public String getCityValue(String cityName, String xpath) {
        String value = new String();
        WebElement city = null;
        try {
            city = driver.findElement(By.xpath(".//*[@class='temperatureContainer']/following-sibling::div[@class='cityText'][contains(text(),'" + cityName + "')]"));
        } catch (NoSuchElementException ns) {
            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            return "";
        }
        // for(WebElement city : map.citiesDisplayed)
        //   {
        if (city.getText().equalsIgnoreCase(cityName)) {
            Assert.assertTrue(true);
            city.click();
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("leaflet-popup-content")));
            value = driver.findElement(By.xpath(xpath)).getText();
            if (value.contains(("Degrees"))) {
                value = value.replace("Temp in Degrees:", "").trim();
            } else if (value.contains("Humidity")) {
                value = value.replace("Humidity: ", "").trim();
                value = value.replace("%", "").trim();
            }
            city.click();
            return value;
        } else
        {
            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            return "";
            //      }
        }
    }
}
