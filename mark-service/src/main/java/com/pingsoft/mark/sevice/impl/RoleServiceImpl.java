package com.pingsoft.mark.sevice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pingsoft.mark.mapper.RoleMapper;
import com.pingsoft.mark.pojo.Role;
import com.pingsoft.mark.sevice.IRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wkw
 * @since 2020-06-12
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
