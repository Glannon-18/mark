package com.pingsoft.mark.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pingsoft.mark.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author wkw
 * @since 2020-06-12
 */
public interface UserMapper extends BaseMapper<User> {

    User selectById(Long id);

    User selectUserWithRolesByAccount(@Param("account") String account);

    Integer selectCountByAccount(@Param("account") String account, @Param("userId") Long userId);

    IPage<User> pageUser(Page<User> page, @Param("name") String name);

}
