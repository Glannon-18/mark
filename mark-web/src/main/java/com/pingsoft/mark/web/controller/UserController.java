package com.pingsoft.mark.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pingsoft.mark.pojo.RespBean;
import com.pingsoft.mark.pojo.User;
import com.pingsoft.mark.sevice.IUserService;
import com.pingsoft.mark.web.Constant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wkw
 * @since 2020-06-12
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @GetMapping("/info")
    public RespBean info(@RequestParam String token) {
        Claims claims = Jwts.parser().setSigningKey(Constant.TOKEN_KEY).parseClaimsJws(token.replace("Bearer", ""))
                .getBody();
        Long id = (Long) claims.get("id");
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.select("username", "photo").eq("id", id);
        User user = userService.getOne(userQueryWrapper);
        return RespBean.ok(user);

    }




}
