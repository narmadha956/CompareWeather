package tests;

import Handler.HomeAction;
import Handler.MapAction;
import Handler.APIHandler;
import Handler.WeatherBuilder;
import io.restassured.response.Response;
import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;
import utilities.ConfigDetails;
import org.testng.annotations.Test;

import java.io.StringReader;
import java.util.Map;

public class CommonTests extends BaseTest{
    
    String uiPage = new String();
    String endpoint = new String();
    ConfigDetails.scenarios[] scenarios = null;

    @BeforeClass
    public void initialiseTest()
    {
         scenarios = runner.getSuite();
         uiPage = scenarios[0].getUiPage();
         endpoint = scenarios[0].getEndpoint();

        HomeAction actions = new HomeAction(driver);
        actions.navigateToPage(uiPage);
    }
    @Test(dataProvider="suite")
    public void comparator(String city)
    {
        SoftAssert softAssert = new SoftAssert();
        Map<String, String> queryParams = defaultQueryParams;
        queryParams.put("q",city);
        Response  apiResponse = APIHandler.apiTest(queryParams,endpoint);
        MapAction map = new MapAction(driver);
        map.searchCity(city);
        for( ConfigDetails.scenarios scenario : scenarios)
            {
                if(!scenario.getEndpoint().equals(endpoint))
                {
                    apiResponse = APIHandler.apiTest(queryParams,scenario.getEndpoint());
                }
                if(!scenario.getUiPage().equals(uiPage))
                {
                    HomeAction actions = new HomeAction(driver);
                    actions.navigateToPage(uiPage);
                }
                String apiValue = apiResponse.jsonPath().getString(scenario.getJsonPath());
                String uiValue = map.getCityValue(city,scenario.getUiPath());
                if(uiValue=="")
                    softAssert.fail("City not present in map "+city);
                if(apiResponse.statusCode()!=200)
                    softAssert.fail("API returned status "+apiResponse.statusCode()+" for city"+city);
                if(apiResponse.statusCode()==200 && uiValue!="" )
                softAssert.assertTrue(varianceLogic(scenario.getVariance(),scenario.getMetric(),apiValue,uiValue),"Variance for"+scenario.getMetric()+" exceeds difference");
            }
        softAssert.assertAll();
    }
}