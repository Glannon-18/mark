package com.pingsoft.mark.web.controller;

import com.pingsoft.mark.pojo.RespBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = Exception.class)
    public RespBean error(Exception ex) {
        ex.printStackTrace();
        return RespBean.error(ex.getMessage() + "操作发生异常！");
    }
}
