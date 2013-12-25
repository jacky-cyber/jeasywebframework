package com.jeasywebframework.utils;

import com.jeasywebframework.utils.json.WebJsonConfig;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jeasywebframework@gmail.com on 13-12-18.
 */
public abstract class AjaxUtil {


    public static JSONObject jqGridJson(Page page) {
        JSONObject json = new JSONObject();
        if (page == null) {
            json.put("records", 0);
            json.put("page", 1);
            json.put("rows", ArrayUtils.EMPTY_OBJECT_ARRAY);
            return json;
        }
        json.put("total", page.getTotalPages());
        json.put("page", page.getNumber() + 1);
        json.put("records", page.getTotalElements());
        List dataList = page.getContent();
        if (dataList != null && dataList.size() > 0) {
            JSONArray jsonArray = new JSONArray();
            for (Object item : dataList) {
                Class c = item.getClass();
                if (c == int.class || c == Integer.class ||
                        c == long.class || c == Long.class ||
                        c == float.class || c == Float.class ||
                        c == double.class || c == Double.class ||
                        c == boolean.class || c == Boolean.class ||
                        c == byte.class || c == Byte.class ||
                        c == char.class || c == Byte.class ||
                        c == short.class || c == Short.class ||
                        c == String.class) {
                    jsonArray.add(item);
                } else {
                    jsonArray.add(JSONObject.fromObject(item, WebJsonConfig.getInstance()));
                }
            }
            json.put("rows", jsonArray);
        } else {
            json.put("rows", ArrayUtils.EMPTY_OBJECT_ARRAY);
        }
        return json;

    }


    public static JSONObject success() {
        return AjaxUtil.success(null);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static JSONObject output(boolean success, String msg, JSONObject extData) {
        Map map = new HashMap();
        map.put("_success", success);
//        map.put("success", success);

        map.put("_code", success ? 0 : 1);
        map.put("_msg", msg);
//        map.put("errorMessage", msg);

        if (extData == null || extData.equals("null")) {
            extData = new JSONObject();
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.putAll(map);
        jsonObject
                .put("data", extData);

        return jsonObject;
    }

    public static JSONObject success(String msg) {
        return output(true, msg, null);
    }

    public static JSONObject success(String msg, Object object) {
        JSONObject json = JSONObject.fromObject(object, WebJsonConfig.getInstance());
        return output(true, msg, json);
    }


    public static JSONObject failure(String msg) {
        return output(false, msg, null);
    }

    public static JSONObject failure(String msg, Object object) {
        JSONObject json = JSONObject.fromObject(object, WebJsonConfig.getInstance());
        return output(false, msg, json);
    }


}
