package Actions;

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

    public void searchCity()
    {
        MapPage map = new MapPage(driver);
        map.citySearch.click();
        map.citySearch.sendKeys("Bengaluru");
        String isChecked = driver.findElement(By.id("Bengaluru")).getAttribute("checked");
        if(!isChecked.equalsIgnoreCase("true"))
            driver.findElement(By.id("Bengaluru")).click();
    }

    public String getCityTemp()
    {
        String temperatue = new String();
        MapPage map = new MapPage(driver);
        for(WebElement city : map.citiesDisplayed)
        {
            if(city.getText().equalsIgnoreCase("Bengaluru"))
            {
                Assert.assertTrue(true);
                city.click();
                temperatue =  map.icon.getText().replace("Temp in Degrees:","").trim();
            }
        }
        return temperatue;
    }






}
