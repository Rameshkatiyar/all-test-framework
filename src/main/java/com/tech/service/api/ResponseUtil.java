package com.tech.service.api;

import com.google.common.base.Strings;
import com.tech.utils.JsonUtil;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

public class ResponseUtil {
    public static JSONObject toJSONObject(Response response){
        String jsonResult = response.then().extract().asString();
        return JsonUtil.readStringToJsonObject(jsonResult);
    }

    public static String getValue(Response response, String key){
        return response
                .getBody()
                .jsonPath()
                .get(Strings.nullToEmpty(key));
    }

    public static String getBodyDataAsString(Response response){
        return response.then().extract().asString();
    }
}
