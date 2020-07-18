package com.pingsoft.mark.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pingsoft.mark.pojo.Role;
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
public interface RoleMapper extends BaseMapper<Role> {

    Role selectById(@Param("id") Long id);

    List<Role> selectByUserId(@Param("userId") Long userId);

}
