package com.pingsoft.mark.web.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pingsoft.mark.Constant;
import com.pingsoft.mark.pojo.Menu;
import com.pingsoft.mark.pojo.RespBean;
import com.pingsoft.mark.pojo.RespPageBean;
import com.pingsoft.mark.pojo.Role;
import com.pingsoft.mark.sevice.IMenuService;
import com.pingsoft.mark.sevice.IRoleService;
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
@RequestMapping("/system/role")
public class RoleController {

    @Resource
    private IRoleService roleService;

    @Resource
    private IMenuService menuService;


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
    public RespPageBean page(@RequestParam Integer currentPage, @RequestParam String name) {
        IPage<Role> page = roleService.page(currentPage, name);
        return new RespPageBean(page.getTotal(), page.getRecords(), page.getSize());
    }

    @GetMapping("/userId/{userId}")
    public RespBean selectByUserId(@PathVariable Long userId) {
        List<Role> roles = roleService.selectByUserId(userId);
        return RespBean.ok(roles);
    }


    @GetMapping("/getMenuTree")
    public RespBean getMenuTree() {
        List<Menu> menuTree = menuService.getMenuTree();
        return RespBean.ok(menuTree);
    }

}
