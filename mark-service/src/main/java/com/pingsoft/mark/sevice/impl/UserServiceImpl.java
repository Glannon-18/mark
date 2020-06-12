package com.pingsoft.mark.sevice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pingsoft.mark.mapper.UserMapper;
import com.pingsoft.mark.pojo.User;
import com.pingsoft.mark.sevice.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wkw
 * @since 2020-06-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
