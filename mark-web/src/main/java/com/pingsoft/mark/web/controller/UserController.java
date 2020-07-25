package com.pingsoft.mark.web.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pingsoft.mark.Constant;
import com.pingsoft.mark.pojo.RespBean;
import com.pingsoft.mark.pojo.RespPageBean;
import com.pingsoft.mark.pojo.Role;
import com.pingsoft.mark.pojo.User;
import com.pingsoft.mark.sevice.IRoleService;
import com.pingsoft.mark.sevice.IUserService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    private IRoleService roleService;


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

    @GetMapping("/selectAllRole")
    public RespBean selectAllRole() {
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        roleQueryWrapper.eq("discard", Constant.NOT_DELETE);
        List<Role> roles = roleService.getBaseMapper().selectList(roleQueryWrapper);
        return RespBean.ok(roles);
    }

}
