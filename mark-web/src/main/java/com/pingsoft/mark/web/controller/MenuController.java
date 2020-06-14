package com.pingsoft.mark.web.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wkw
 * @since 2020-06-12
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @GetMapping("/")
    public String b() {
        return "b";
    }

}
