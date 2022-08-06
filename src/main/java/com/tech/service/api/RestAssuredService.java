package com.tech.service.api;

import com.tech.constants.MediaType;
import com.tech.constants.RequestMethod;
import com.tech.utils.JsonUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.testng.util.Strings;

import java.util.Map;

@Slf4j
public class RestAssuredService {
    public static String getApplicationSession(String username, String password){
        //Write the code to get the session with username and password.
        return "";
    }

    public static Response sendRequestWithBody(String url, RequestMethod requestMethod, Object inputObject, String session){
        RequestSpecification requestSpecification = RestAssured.given();
        if (null != inputObject){
            requestSpecification.body(JsonUtil.mapObjectToJson(inputObject));
        }
        return submitRequest(url, requestMethod, session, requestSpecification);

    }

    public static Response sendRequestWithPathParameters(String url, RequestMethod requestMethod, Map<String, Object> inputParameters, String session){
        RequestSpecification requestSpecification = RestAssured.given()
                .pathParams(inputParameters);
        return submitRequest(url, requestMethod, session, requestSpecification);
    }

    /**
     * @param url
     * @param requestMethod
     * @param session
     * @param requestSpecification
     * @return
     */
    private static Response submitRequest(String url,
                                   RequestMethod requestMethod,
                                   String session,
                                   RequestSpecification requestSpecification) {
        if (!Strings.isNullOrEmpty(session)) {
            requestSpecification = requestSpecification
                    .header("Cookie", session);
        }
        switch (requestMethod) {
            case POST:
                return requestSpecification
                        .contentType(ContentType.JSON)
                        .post(url);
            case PUT:
                return requestSpecification
                        .contentType(ContentType.JSON)
                        .put(url);
            case DELETE:
                return requestSpecification
                        .delete(url);
            case GET:
            default:
                return requestSpecification
                        .accept(MediaType.APPLICATION_JSON)
                        .get(url);
        }
    }
}
