package com.example.demo;

/**
 * FileName: BusinesException
 * Date: 2021/1/13 8:53 下午
 * Description: 自定义异常类
 * @Author：guycui
 */
public class BusinessException extends RuntimeException{
    /**
     * 自定义错误码
     */
    private Integer code;
    /**
     * 自定义构造器，必须输入错误码及内容
     */
    public BusinessException(int code,String msg){
        super(msg);
        this.code = code;
    }
    public Integer getCode(){
        return code;
    }
    public void setCode(Integer code){
        this.code = code;
    }
}
