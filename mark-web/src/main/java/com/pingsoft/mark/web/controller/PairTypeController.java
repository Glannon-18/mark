package com.pingsoft.mark.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pingsoft.mark.Constant;
import com.pingsoft.mark.pojo.PairType;
import com.pingsoft.mark.pojo.RespBean;
import com.pingsoft.mark.pojo.RespPageBean;
import com.pingsoft.mark.service.IPairTypeService;
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
@RestController
@RequestMapping("/system/type")
public class PairTypeController {

    @Resource
    private IPairTypeService pairTypeService;

    @PostMapping("/")
    public RespBean add(@RequestParam String name) {
        PairType pairType = new PairType();
        pairType.setName(name);
        pairType.setCreateTime(LocalDateTime.now());
        pairType.setDiscard(Constant.NOT_DELETE);
        pairTypeService.save(pairType);
        return RespBean.ok("添加类型成功！");
    }

    @PutMapping("/")
    public RespBean edit(@RequestParam String id, @RequestParam String name) {
        UpdateWrapper<PairType> pairTypeUpdateWrapper = new UpdateWrapper<>();
        pairTypeUpdateWrapper.set("name", name).eq("id", Long.valueOf(id));
        pairTypeService.update(pairTypeUpdateWrapper);
        return RespBean.ok("修改类型成功！");
    }

    @DeleteMapping("/{id}")
    public RespBean delete(@PathVariable String id) {
        pairTypeService.removeById(Long.valueOf(id));
        return RespBean.ok("删除小组成功！");
    }

    @GetMapping("/{id}")
    public RespBean getById(@PathVariable String id) {
        return RespBean.ok(pairTypeService.getById(id));
    }

    @GetMapping("/page")
    public RespPageBean page(@RequestParam String currentPage, @RequestParam String name) {
        Page<PairType> pairTypePage = new Page<>(Integer.valueOf(currentPage), Constant.PAGE_SIZE);
        QueryWrapper<PairType> pairTypeQueryWrapper = new QueryWrapper<>();
        pairTypeQueryWrapper.like("name", name);
        Page<PairType> page = pairTypeService.page(pairTypePage, pairTypeQueryWrapper);
        return new RespPageBean(page.getTotal(), page.getRecords(), page.getSize());
    }
}
