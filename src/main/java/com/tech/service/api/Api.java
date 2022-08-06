package com.tech.service.api;

import com.tech.constants.RequestMethod;
import com.tech.utils.PathUtil;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;

@Slf4j
public class Api {
    private String baseUri;
    public Api(String baseUri){
        this.baseUri = baseUri;
    }

    public Response get(String urlPath){
        return RestAssuredService.sendRequestWithBody(
                PathUtil.concatPaths(baseUri, urlPath),
                RequestMethod.GET,
                null,
                null
        );
    }

    public Response post(String urlPath, JSONObject payload){
        return RestAssuredService.sendRequestWithBody(
                PathUtil.concatPaths(baseUri, urlPath),
                RequestMethod.POST,
                payload,
                null
        );
    }

    //TODO: Implement other method also
}
