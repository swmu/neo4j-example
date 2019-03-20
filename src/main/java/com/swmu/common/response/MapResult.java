package com.swmu.common.response;

import java.util.HashMap;

/**
 * @author seven.mu
 * @version 1.0
 * @description Map返回
 * @date Create in 2018/12/13 18:01
 */
public class MapResult extends HashMap<String, Object> {
    private final long serialVersionUID = 1L;

    public MapResult() {
    }

    public MapResult ok() {
        return ok("操作成功！");
    }

    public MapResult ok(String message) {
        return ok(0, message);
    }

    public MapResult ok(int code, String message) {
        MapResult resultMap = new MapResult();
        resultMap.put("code", code);
        resultMap.put("msg", message);
        return resultMap;
    }

    public MapResult error() {
        return error("操作失败！");
    }

    public MapResult error(String messag) {
        return error(1, messag);
    }

    public MapResult error(int code, String message) {
        return ok(code, message);
    }

    public MapResult setCode(int code) {
        super.put("code", code);
        return this;
    }

    public MapResult setMessage(String message) {
        super.put("msg", message);
        return this;
    }

    @Override
    public MapResult put(String key, Object object) {
        super.put(key, object);
        return this;
    }
}