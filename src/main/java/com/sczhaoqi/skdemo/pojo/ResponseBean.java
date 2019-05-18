package com.sczhaoqi.skdemo.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author sczhaoqi
 * @date 2019/5/4 14:59
 */
@Data
public class ResponseBean<T>
        implements Serializable
{
    private T data;
    private String message;
    private Integer code;

    public static <T> ResponseBean<T> ok(T data){
        ResponseBean<T> responseBean = new ResponseBean<>();
        responseBean.setCode(1);
        responseBean.setData(data);
        responseBean.setMessage("SUCCESS");
        return responseBean;
    }
    public static <T> ResponseBean<T> ok(T data,String message){
        ResponseBean<T> responseBean = new ResponseBean<>();
        responseBean.setCode(1);
        responseBean.setData(data);
        responseBean.setMessage(message);
        return responseBean;
    }
    public static <T> ResponseBean<T> warn(T data){
        ResponseBean<T> responseBean = new ResponseBean<>();
        responseBean.setCode(2);
        responseBean.setData(data);
        responseBean.setMessage("WARN");
        return responseBean;
    }
    public static <T> ResponseBean<T> warn(T data,String message){
        ResponseBean<T> responseBean = new ResponseBean<>();
        responseBean.setCode(2);
        responseBean.setData(data);
        responseBean.setMessage(message);
        return responseBean;
    }
    public static <T> ResponseBean<T> error(){
        ResponseBean<T> responseBean = new ResponseBean<>();
        responseBean.setCode(3);
        responseBean.setMessage("操作失败");
        return responseBean;
    }
    public static <T> ResponseBean<T> error(String message){
        ResponseBean<T> responseBean = new ResponseBean<>();
        responseBean.setCode(3);
        responseBean.setMessage(message);
        return responseBean;
    }

    public static <T> ResponseBean<T> sysError(){
        ResponseBean<T> responseBean = new ResponseBean<>();
        responseBean.setCode(4);
        responseBean.setMessage("系统错误");
        return responseBean;
    }
    public static <T> ResponseBean<T> sysError(String message){
        ResponseBean<T> responseBean = new ResponseBean<>();
        responseBean.setCode(4);
        responseBean.setMessage(message);
        return responseBean;
    }
}
