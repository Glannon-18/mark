package com.pingsoft.mark.web.controller;

import com.pingsoft.mark.pojo.RespBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public RespBean error(Exception ex) {
        ex.printStackTrace();
        return RespBean.error("操作发生异常！" + ex.getMessage());
    }
}
