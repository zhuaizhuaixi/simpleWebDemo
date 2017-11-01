package com.fzu.demo.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzx
 */
public class JSONResult {
    public final static String KEY_CODE = "code";
    public final static String KEY_NOTE = "note";
    public final static int KEY_CODE_SUCCESS = 1;
    public final static int KEY_CODE_FAIL = -1;
    public final static String KEY_EXCEPTION = "exception";

    private int code;
    private String note;
    private String exception;
    Map<String, Object> dataMap = new HashMap<>();

    private JSONResult() {
        this.code = KEY_CODE_SUCCESS;
        this.note = "";
    }

    public static JSONResult build() {
        return new JSONResult();
    }
    public JSONObject getJSON(){
        this.dataMap.put(KEY_CODE,code);
        this.dataMap.put(KEY_NOTE,note);
        return (JSONObject) JSON.toJSON(dataMap);
    }
    public JSONResult set(String key, Object data) {
        this.dataMap.put(key, data);
        return this;
    }
    public Object get(String key) {
        return dataMap.get(key);
    }
    public Object remove(String key) {
        return dataMap.remove(key);
    }
    public JSONResult set(Map<String, Object> dataMap) {
        this.dataMap.putAll(dataMap);
        return this;
    }

    public JSONResult setCodeAndNote(int code, String note) {
        this.code = code;
        this.note = note;
        return this;
    }

    public JSONResult setCode(int code) {
        this.code = code;
        return this;
    }

    public JSONResult setNote(String note) {
        this.note = note;
        return this;
    }

    public JSONResult setException(String exception) {
        this.exception = exception;
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getNote() {
        return note;
    }
}
