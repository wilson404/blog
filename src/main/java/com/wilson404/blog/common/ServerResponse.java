package com.wilson404.blog.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServerResponse<T> implements Serializable {
    @JsonProperty
    private int status;
    @JsonProperty
    private String msg;
    @JsonProperty
    private T data;

    private ServerResponse(int status) {
        this.status = status;
    }

    private ServerResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private ServerResponse(int status, T data) {
        this.status = status;
        this.data = data;
    }

    private ServerResponse(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    public static <T> ServerResponse<T> createBySuccess() {
        return new ServerResponse<>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> ServerResponse<T> createBySuccess(T t) {
        return new ServerResponse<>(ResponseCode.SUCCESS.getCode(), t);
    }

    public static <T> ServerResponse<T> createBySuccess(String msg, T t) {
        return new ServerResponse<>(ResponseCode.SUCCESS.getCode(), msg, t);
    }

    public static <T> ServerResponse<T> createBySuccessMessage(String msg) {
        return new ServerResponse<>(ResponseCode.SUCCESS.getCode(), msg);
    }
    public static <T> ServerResponse<T> createByError(){
        return new ServerResponse<>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }
    public static <T> ServerResponse<T> createByError(ResponseCode responseCode){
        return new ServerResponse<>(responseCode.getCode(),responseCode.getDesc());
    }
    public static <T> ServerResponse<T> createByErrorMessage(String msg){
        return new ServerResponse<>(ResponseCode.ERROR.getCode(),msg);
    }

    @Override
    public String toString() {
        return "ServerResponse{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
