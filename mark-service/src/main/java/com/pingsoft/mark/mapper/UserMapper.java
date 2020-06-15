package com.pingsoft.mark.mapper;

import com.pingsoft.mark.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wkw
 * @since 2020-06-12
 */
public interface UserMapper extends BaseMapper<User> {

    User selectUserWithRolesByAccount(@Param("account") String account);

}
