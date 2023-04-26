package com.kd.appweather.beans;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMapper {
    private  static ObjectMapper objectMapper;

    public static ObjectMapper getInstance(){
        if(objectMapper==null) {
            synchronized (JsonMapper.class) {
                if (objectMapper == null) {
                    objectMapper = new ObjectMapper();
                    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                            .configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false)
                            .configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false)
                            .configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false)
                            .configure(DeserializationFeature.WRAP_EXCEPTIONS, true);
                }
            }
        }
        return objectMapper;
    }
}
