package helper;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import org.slf4j.event.Level;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static tests.BaseTest.attachToRP;

public abstract class APIHandler  {

    public static Response apiTest(Map<String,String> queryParam, String endpoint ) {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final String utf8 = StandardCharsets.UTF_8.name();
        PrintStream printStream  = null;
        try {
            printStream = new PrintStream(baos, true, utf8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        RestAssured.config = RestAssured.config().logConfig(new LogConfig().defaultStream(printStream));


        Response response = given().filter(new RequestLoggingFilter(printStream)).filter(new ResponseLoggingFilter(printStream)).relaxedHTTPSValidation().queryParams(queryParam)
                .when().get(endpoint);
        String data = new String();
        try {
             data = baos.toString(utf8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if(response.statusCode()!=200)
            attachToRP(Level.ERROR,data);

        return response;
    }

}
