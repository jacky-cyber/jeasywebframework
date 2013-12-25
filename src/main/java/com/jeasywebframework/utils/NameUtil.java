package com.jeasywebframework.utils;

import org.apache.commons.lang.StringUtils;

/**
 * Created by jeasywebframework@gmail.com on 13-12-21.
 */
public class NameUtil {


    public static String getSmallName(String name) {
        String bigName = NameUtil.getBigName(name);
        String a = bigName.substring(0, 1);
        String b = bigName.substring(1, bigName.length());

        return a.toLowerCase() + b;
    }


    public static String getBigName(String name) {
        String[] ss = StringUtils.split(name, "_");
        String[] s2 = new String[ss.length];
        int i = 0;
        for (String s : ss) {
            if (s.length() > 1) {
                String a = s.substring(0, 1);
                String b = s.substring(1, s.length());
                String t = a.toUpperCase() + b;
                s2[i++] = t;
            } else {
                String t = s.toUpperCase();
                s2[i++] = t;
            }
        }

        return StringUtils.join(s2);
    }
}
