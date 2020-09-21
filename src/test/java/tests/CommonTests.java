package tests;

import helper.HomeAction;
import helper.MapAction;
import helper.APIHandler;
import com.epam.reportportal.service.ReportPortal;
import io.restassured.response.Response;
import org.testng.ITest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import utilities.ConfigDetails;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

public class CommonTests extends BaseTest implements ITest {
    private ThreadLocal<String> testName = new ThreadLocal<>();
    String uiPage = new String();
    String endpoint = new String();
    ConfigDetails.scenarios[] scenarios = null;

    @BeforeClass
    public void initialiseTest()
    {
        driver.get(runner.getBase().getBaseWebsite());
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
                {
                    ReportPortal.emitLog("API " +scenario.getMetric()+" is "+apiValue, "INFO", new Date());
                    ReportPortal.emitLog("UI Value "+scenario.getMetric()+" is "+uiValue, "INFO", new Date());
                    softAssert.assertTrue(varianceLogic(scenario.getVariance(),scenario.getMetric(),apiValue,uiValue),"Variance for "+scenario.getMetric()+" exceeds allowed limit. Allowed is "+scenario.getVariance());
                }
            }
        softAssert.assertAll();
    }

    @BeforeMethod(alwaysRun = true)
    public void BeforeMethod(Method method, Object[] testData) {

        if (method.getName().equals("comparator")) {
            String testcaseName = testData[0].toString();
            testName.set("compare "+testcaseName);
        } else
            testName.set(method.getName());

    }

    @Override
    public String getTestName() {
        return testName.get();
    }
}
