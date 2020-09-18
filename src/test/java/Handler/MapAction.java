package Handler;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.MapPage;

public class MapAction {
    private MapPage mapPageObjects;
    WebDriver driver;

    public MapAction(WebDriver driver) {
        mapPageObjects = new MapPage(driver);
        this.driver=driver;
    }

    public void searchCity(String city)
    {
        MapPage map = new MapPage(driver);
        map.citySearch.click();
        map.citySearch.clear();
        map.citySearch.sendKeys(city);
        String isChecked = driver.findElement(By.id(city)).getAttribute("checked");
        if(!isChecked.equalsIgnoreCase("true"))
            driver.findElement(By.id(city)).click();
    }

    public String getCityTemp(String cityName, String xpath)
    {
        String value = new String();
        MapPage map = new MapPage(driver);
        searchCity(cityName);
        for(WebElement city : map.citiesDisplayed)
        {
            if(city.getText().equalsIgnoreCase(cityName))
            {
                Assert.assertTrue(true);
                city.click();
                value =  driver.findElement(By.xpath(xpath)).getText();
                if(value.contains(("Degrees")))
                {
                   value = value.replace("Temp in Degrees:","").trim();
                }
                if(value.contains("Humidity"))
                {
                    value = value.replace("Humidity: ","").trim();
                    value = value.replace("%","").trim();
                }
                return value;
            }
        }
        return "";

    }






}
