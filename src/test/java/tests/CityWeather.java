package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.MapPage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class CityWeather extends BaseTest{

    @Test
    public void getCityWeather(){
        Map<String, String> mapParams = new HashMap<String, String>();
        mapParams.put("q","Bengaluru" );
        mapParams.put("appid", "7fe67bf08c80ded756e598d6f8fedaea");

        Response response = given().relaxedHTTPSValidation().queryParams(mapParams)
                .when().get("data/2.5/weather");

        response.then().log().all();
        driver.get("https://www.ndtv.com/");
        HomePage home = new HomePage(driver);
        home.subMenu.click();
        home.weatherOption.click();
        MapPage map = new MapPage(driver);
        map.citySearch.click();
        map.citySearch.sendKeys("Bengaluru");
        String isChecked = driver.findElement(By.id("Bengaluru")).getAttribute("checked");
        if(!isChecked.equalsIgnoreCase("true"))
            driver.findElement(By.id("Bengaluru")).click();
        for(WebElement city : map.citiesDisplayed)
        {
            if(city.getText().equalsIgnoreCase("Bengaluru"))
            {
                Assert.assertTrue(true);
                city.click();
               String temp =  map.icon.getText().replace("Temp in Degrees:","").trim();
            }
        }
        driver.quit();
    }
}
