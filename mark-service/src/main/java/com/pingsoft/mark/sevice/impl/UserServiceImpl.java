package com.pingsoft.mark.sevice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pingsoft.mark.mapper.UserMapper;
import com.pingsoft.mark.pojo.User;
import com.pingsoft.mark.sevice.IUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wkw
 * @since 2020-06-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService, UserDetailsService {


    @Override
    public User selectUserWithRolesByAccount(String account) {
        User user = getBaseMapper().selectUserWithRolesByAccount(account);
        return user;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = selectUserWithRolesByAccount(username);
        if (ObjectUtils.isEmpty(user)) {
            throw new UsernameNotFoundException("账号或用户名错误！");
        }
        return user;
    }
}
