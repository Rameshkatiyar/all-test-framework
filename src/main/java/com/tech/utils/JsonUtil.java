package com.tech.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Map;

@Slf4j
public class JsonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static  <T> T mapJsonToObject(String jsonString, Class<T> tClass) {
        try {
            return objectMapper.readValue(jsonString, tClass);
        } catch (Exception e) {
            log.error("Failed Json To Object mapping. {}", e.getMessage());
            return null;
        }
    }

    public static String mapObjectToJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            log.error("Failed Object to Json mapping. {}", e.getMessage());
            return "";
        }
    }

    public static Map<String, String> getJsonToKeyValueMap(String jsonString) {
        try {
            return objectMapper.readValue(jsonString, Map.class);
        } catch (Exception e) {
            log.error("Failed Json To Map mapping. {}", e.getMessage());
            return null;
        }
    }

    public static JSONObject readStringToJsonObject(String jsonString) {
        JSONParser jsonParser = new JSONParser();
        try {
            return (JSONObject) jsonParser.parse(jsonString);
        } catch (ParseException e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
