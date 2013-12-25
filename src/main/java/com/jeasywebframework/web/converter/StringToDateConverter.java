package com.jeasywebframework.web.converter;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jeasywebframework@gmail.com on 13-12-18.
 */
public class StringToDateConverter implements Converter<String, Date> {



    private static final Logger logger = LoggerFactory.getLogger(StringToDateConverter.class);



    @Override
    public Date convert(String source) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(source);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("String converter to Date error.", ex);
        }

        return date;
    }
}
