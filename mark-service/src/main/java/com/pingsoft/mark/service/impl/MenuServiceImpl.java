package com.pingsoft.mark.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pingsoft.mark.mapper.MenuMapper;
import com.pingsoft.mark.pojo.Menu;
import com.pingsoft.mark.service.IMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Resource
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getAllMenusWithRole() {
        return menuMapper.getAllMenusWithRole();
    }

    @Override
    public List<Menu> getMenuSideBar(Long userId) {
        return menuMapper.getMenuSideBar(userId);
    }

    @Override
    public List<Menu> getMenuTree() {
        return menuMapper.getMenuTree();
    }
}
