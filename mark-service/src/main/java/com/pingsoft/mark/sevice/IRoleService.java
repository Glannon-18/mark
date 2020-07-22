package com.pingsoft.mark.sevice;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pingsoft.mark.pojo.Role;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wkw
 * @since 2020-06-12
 */
public interface IRoleService extends IService<Role> {

    void add(JSONObject jsonObject);

    void deleteById(Long id);

    IPage<Role> page(Integer currentPage,String name);

    Role selectById(Long id);

    List<Role> selectByUserId(Long userId);
}
