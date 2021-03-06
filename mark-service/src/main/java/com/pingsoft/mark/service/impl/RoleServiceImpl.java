package com.pingsoft.mark.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pingsoft.mark.Constant;
import com.pingsoft.mark.mapper.RoleMapper;
import com.pingsoft.mark.pojo.Role;
import com.pingsoft.mark.pojo.Role_menu;
import com.pingsoft.mark.service.IRoleService;
import com.pingsoft.mark.service.IRole_menuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wkw
 * @since 2020-06-12
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Resource
    private IRole_menuService role_menuService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(JSONObject jsonObject) {
        String id = jsonObject.getString("id");
        String name = jsonObject.getString("name");
        String nameZh = jsonObject.getString("nameZh");
        ArrayList<Integer> menus = (ArrayList<Integer>) jsonObject.get("menus");
        Role role;
        if (!ObjectUtils.isEmpty(id)) {
            role = getBaseMapper().selectById(Long.valueOf(id));
            QueryWrapper<Role_menu> role_menuQueryWrapper = new QueryWrapper<>();
            role_menuQueryWrapper.eq("rid", Long.valueOf(id));
            role_menuService.getBaseMapper().delete(role_menuQueryWrapper);
            role.setName(name);
            role.setNameZh(nameZh);
            updateById(role);
        } else {
            role = new Role();
            role.setName(name);
            role.setNameZh(nameZh);
            role.setCreate_time(LocalDateTime.now());
            role.setDiscard(Constant.NOT_DELETE);
            save(role);
        }
        if (!ObjectUtils.isEmpty(menus)) {
            ArrayList<Role_menu> role_menus = new ArrayList<>();
            menus.stream().forEach(t -> {
                Role_menu role_menu = new Role_menu();
                role_menu.setRid(role.getId());
                role_menu.setMid(t.longValue());
                role_menus.add(role_menu);
            });
            role_menuService.saveBatch(role_menus);
        }
    }

    @Override
    public void deleteById(Long id) {
        getBaseMapper().deleteById(id);
    }

    @Override
    public IPage<Role> page(Integer currentPage, String name) {
        Page page = new Page(currentPage, Constant.PAGE_SIZE);
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        roleQueryWrapper.eq("discard", Constant.NOT_DELETE);
        if (!StringUtils.isEmpty(name)) {
            roleQueryWrapper.like("nameZh", name);
        }
        return page(page, roleQueryWrapper);
    }

    @Override
    public Role selectById(Long id) {
        return getBaseMapper().selectById(id);
    }

    @Override
    public List<Role> selectByUserId(Long userId) {
        return getBaseMapper().selectByUserId(userId);
    }
}
