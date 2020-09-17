package tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class APITests extends BaseTest{

    @Test(groups = "temp")
    public void apiTest()
    {
        Map<String, String> mapParams = new HashMap<String, String>();
        mapParams.put("q","Bengaluru" );
        mapParams.put("appid", "7fe67bf08c80ded756e598d6f8fedaea");
        Response response = given().relaxedHTTPSValidation().queryParams(mapParams)
                .when().get("data/2.5/weather");
        response.then().log().all();
        String temp = response.jsonPath().getString("main.temp");
        double kelvin = Double.parseDouble(temp);
        double apiTemp = kelvin - 273.15;
    }
}
