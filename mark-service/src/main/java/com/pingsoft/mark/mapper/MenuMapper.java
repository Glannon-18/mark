package com.pingsoft.mark.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pingsoft.mark.pojo.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author wkw
 * @since 2020-06-12
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> getAllMenusWithRole();

    List<Menu> getMenuSideBar(@Param("userId") Long userId);

}
