package com.swmu.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @author YuanJingshan
 * @version 1.0
 * @description 封装fastjson
 * @date Create in 2018/12/17 12:22
 */
public class FastJsonUtis {

    public static String toString(JSONObject object) {
        return JSONObject.toJSONString(object, SerializerFeature.WriteMapNullValue);
    }
}
