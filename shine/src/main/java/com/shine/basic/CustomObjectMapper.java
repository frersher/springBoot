package com.shine.basic;


import java.text.SimpleDateFormat;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

/**
 * 数据绑定
 * @author jun.zeng
 *
 */
public class CustomObjectMapper extends ObjectMapper {

    public CustomObjectMapper() {
        configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
        setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }
    
    public CustomObjectMapper(String datePattern) {
        configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
        setDateFormat(new SimpleDateFormat(datePattern));
    }
}