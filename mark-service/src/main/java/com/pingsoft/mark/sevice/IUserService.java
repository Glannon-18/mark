package com.pingsoft.mark.sevice;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pingsoft.mark.pojo.User;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wkw
 * @since 2020-06-12
 */
public interface IUserService extends IService<User> {

    User selectById(Long id);

    User selectUserWithRolesByAccount(String account);

    void add(JSONObject jsonObject);

    void update(String id, JSONObject jsonObject);

    Integer selectCountByAccount(String account, Long userId);

    IPage<User> page(String name, Integer currentPage);

    void deleteById(Long id);

}
