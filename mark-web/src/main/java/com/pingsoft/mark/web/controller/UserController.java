package com.pingsoft.mark.web.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pingsoft.mark.pojo.RespBean;
import com.pingsoft.mark.pojo.RespPageBean;
import com.pingsoft.mark.pojo.User;
import com.pingsoft.mark.sevice.IUserService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/system/user")
public class UserController {

    @Resource
    private IUserService userService;


    @PostMapping("/")
    public RespBean createUser(@RequestBody JSONObject jsonObject) {
        userService.add(jsonObject);
        return RespBean.ok("添加用户成功！");
    }

    @GetMapping("/{id}")
    public RespBean getUser(@PathVariable String id) {
        return RespBean.ok(userService.selectById(Long.valueOf(id)));
    }

    @GetMapping("/")
    public RespPageBean listUser(@RequestParam String name, @RequestParam Integer currentPage) {
        IPage<User> page = userService.page(name, currentPage);
        return new RespPageBean(page.getTotal(), page.getRecords(), page.getSize());
    }

    @PutMapping("/{id}")
    public RespBean updateUser(@PathVariable String id, @RequestBody JSONObject jsonObject) {
        userService.update(id, jsonObject);
        return RespBean.ok("修改用户成功！");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteUser(@PathVariable String id) {
        userService.deleteById(Long.valueOf(id));
        return RespBean.ok("删除用户成功！");
    }

    @GetMapping("/check")
    public RespBean checkAccountName(@RequestParam String account, @RequestParam String userId) {
        Integer count = userService.selectCountByAccount(account, StringUtils.isEmpty(userId) ? null : Long.valueOf(userId));
        return RespBean.ok(count);
    }

}
