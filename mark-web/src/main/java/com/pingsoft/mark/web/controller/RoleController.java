package com.pingsoft.mark.web.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pingsoft.mark.pojo.RespBean;
import com.pingsoft.mark.pojo.RespPageBean;
import com.pingsoft.mark.pojo.Role;
import com.pingsoft.mark.sevice.IRoleService;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wkw
 * @since 2020-06-12
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Resource
    private IRoleService roleService;


    @RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.PUT})
    public RespBean add(@RequestBody JSONObject jsonObject) {
        roleService.add(jsonObject);
        return RespBean.ok("操作成功！");
    }


    @DeleteMapping("/{id}")
    public RespBean delete(@PathVariable Long id) {
        roleService.deleteById(id);
        return RespBean.ok("删除成功！");
    }


    @GetMapping("/{id}")
    public RespBean getById(@PathVariable Long id) {
        Role role = roleService.selectById(id);
        return RespBean.ok(role);
    }

    @GetMapping("/")
    public RespPageBean page(@RequestParam Integer currentPage) {
        IPage<Role> page = roleService.page(currentPage);
        return new RespPageBean(page.getTotal(), page.getRecords(), page.getCurrent());
    }

}
