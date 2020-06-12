package com.pingsoft.mark.sevice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pingsoft.mark.mapper.MenuMapper;
import com.pingsoft.mark.pojo.Menu;
import com.pingsoft.mark.sevice.IMenuService;
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
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

}
