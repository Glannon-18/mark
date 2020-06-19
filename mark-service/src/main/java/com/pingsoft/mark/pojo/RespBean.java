package com.pingsoft.mark.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RespBean {
    private Integer code;
    private String msg;
    private Object object;

    public static RespBean build() {
        return new RespBean();
    }

    public static RespBean ok(String msg) {
        return new RespBean(200, msg, null);
    }

    public static RespBean ok(String msg, Object tokenMap) {
        return new RespBean(200, msg, tokenMap);
    }

    public static RespBean ok(Object tokenMap) {
        return new RespBean(200, null, tokenMap);
    }

    public static RespBean ok() {
        return new RespBean(200, null, null);
    }

    public static RespBean error(String msg) {
        return new RespBean(500, msg, null);
    }

    public static RespBean error(String msg, Object tokenMap) {
        return new RespBean(500, msg, tokenMap);
    }

    private RespBean() {
    }

    private RespBean(Integer code, String msg, Object tokenMap) {
        this.code = code;
        this.msg = msg;
        this.object = tokenMap;
    }

    public Integer getCode() {
        return code;
    }

    public RespBean setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public RespBean setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getObject() {
        return object;
    }

    public RespBean setObject(Object object) {
        this.object = object;
        return this;
    }
}
