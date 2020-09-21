package Handler;

import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public abstract class APIHandler  {

    public static Response apiTest(Map<String,String> queryParam, String endpoint ) {
        Response response = given().relaxedHTTPSValidation().queryParams(queryParam)
                .when().get(endpoint);

        return response;
    }

}
