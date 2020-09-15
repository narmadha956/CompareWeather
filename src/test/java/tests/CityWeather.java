package tests;

import org.openqa.selenium.Alert;
import org.testng.annotations.Test;
import pages.HomePage;

import java.util.List;
import java.util.Set;

public class CityWeather extends BaseTest{

    @Test
    public void getCityWeather(){
        driver.get("https://www.ndtv.com/");
        HomePage home = new HomePage(driver);
        Set<String> handles = driver.getWindowHandles();
        System.out.println(handles.size());
        home.subMenu.click();
        home.weatherOption.click();
        driver.quit();
    }
}
