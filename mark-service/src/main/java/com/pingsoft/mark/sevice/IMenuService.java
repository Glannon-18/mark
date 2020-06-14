package com.pingsoft.mark.sevice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pingsoft.mark.pojo.Menu;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wkw
 * @since 2020-06-12
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> getAllMenusWithRole();

    List<Menu> getMenuSideBar(Long userId);

}
