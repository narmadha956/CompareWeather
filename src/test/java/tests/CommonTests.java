package tests;

import Handler.HomeAction;
import Handler.MapAction;
import Handler.APIHandler;
import utilities.ConfigDetails;
import org.testng.annotations.Test;

import java.util.Map;

public class CommonTests extends BaseTest{

    @Test(dataProvider="suite")
    public void comparator(ConfigDetails.scenarios scenario, String[] cityNames)
    {
        HomeAction actions = new HomeAction(driver);
        actions.navigateToWeatherPage();
        MapAction map = new MapAction(driver);


        for(String city:cityNames)
        {
             Map<String, String> queryParams = defaultQueryParams;
            queryParams.put("q",city);

            String apiValue = APIHandler.apiTest(queryParams,scenario.getEndpoint(),scenario.getJsonPath());
            apiCityValues.put(scenario.getMetric()+city,apiValue);

             String uiValue = map.getCityTemp(city,scenario.getUiPath());
            uiCityValues.put(scenario.getMetric()+city,uiValue);
        }

        String variance = scenario.getVariance();


    }







}
