package com.pingsoft.mark.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pingsoft.mark.Constant;
import com.pingsoft.mark.pojo.Group;
import com.pingsoft.mark.pojo.RespBean;
import com.pingsoft.mark.pojo.RespPageBean;
import com.pingsoft.mark.service.IGroupService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wkw
 * @since 2020-07-27
 */
@Controller
@RequestMapping("/system/team")
public class GroupController {

    @Resource
    private IGroupService groupService;


    @PostMapping("/")
    public RespBean add(@RequestParam String name) {
        Group group = new Group();
        group.setName(name);
        group.setDiscard(Constant.NOT_DELETE);
        group.setCreateTime(LocalDateTime.now());
        groupService.save(group);
        return RespBean.ok("添加小组成功！");
    }

    @PutMapping("/")
    public RespBean edit(@RequestParam String id, @RequestParam String name) {
        UpdateWrapper<Group> groupUpdateWrapper = new UpdateWrapper<>();
        groupUpdateWrapper.set("name", name).eq("id", Long.valueOf(id));
        groupService.update(groupUpdateWrapper);
        return RespBean.ok("修改小组成功！");
    }


    @DeleteMapping("/{id}")
    public RespBean delete(@PathVariable String id) {
        groupService.removeById(Long.valueOf(id));
        return RespBean.ok("删除小组成功！");
    }

    @GetMapping("/{id}")
    public RespBean getById(@PathVariable String id) {
        return RespBean.ok(groupService.getById(id));
    }


    @GetMapping("/page")
    public RespPageBean page(@RequestParam String currentPage, @RequestParam String name) {
        Page<Group> groupPage = new Page<>(Integer.valueOf(currentPage), Constant.PAGE_SIZE);
        QueryWrapper<Group> groupQueryWrapper = new QueryWrapper<>();
        groupQueryWrapper.like("name", name);
        Page<Group> page = groupService.page(groupPage, groupQueryWrapper);
        return new RespPageBean(page.getTotal(), page.getRecords(), page.getSize());

    }
}