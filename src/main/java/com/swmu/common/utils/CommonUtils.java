package com.swmu.common.utils;

import java.util.UUID;

/**
 * @author jingshan
 * @version 1.0
 * @description
 * @date 2018/12/25
 */
public class CommonUtils {
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
}