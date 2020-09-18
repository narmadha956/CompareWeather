package Handler;

import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public abstract class APIHandler  {

    public static String apiTest(Map<String,String> queryParam, String endpoint, String jsonPath ) {
        Response response = given().relaxedHTTPSValidation().queryParams(queryParam)
                .when().get(endpoint);
        String temp = response.jsonPath().getString(jsonPath);

        return temp;
    }

}
