package com.webshell.util;

import lombok.Data;

/**
 * @author GuyCui
 */
@Data
public class ExtResult {
    private Boolean success;
    private Object data;
    private String message;
    public static ExtResult success(Object obj,String message) {
        ExtResult result = new ExtResult();
        result.setSuccess(true);
        result.setData(obj);
        result.setMessage(message);
        return result;
    }
    public static ExtResult failure(Object obj,String message) {
        ExtResult result = new ExtResult();
        result.setSuccess(false);
        result.setData(obj);
        result.setMessage(message);
        return result;
    }
}
