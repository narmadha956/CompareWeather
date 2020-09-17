package tests;

import Actions.HomeAction;
import Actions.MapAction;
import builders.WeatherConditions;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.MapPage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class UITests extends BaseTest{

    List<WeatherConditions> allCities = null;


    @Test(groups = "temp")
    public void uiTest()
    {

        HomeAction actions = new HomeAction(driver);
        actions.navigateToWeatherPage();
        MapAction map = new MapAction(driver);
        String temp2=new String();
        temp2= map.getCityTemp();
        double uiTemperature = Double.parseDouble(temp2);
        driver.quit();

    }

    @Test(dependsOnGroups =  "temp")
    public void getCityWeather(){
        uiTest();
    }
}
