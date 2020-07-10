package com.pingsoft.mark.sevice.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pingsoft.mark.Constant;
import com.pingsoft.mark.mapper.UserMapper;
import com.pingsoft.mark.pojo.User;
import com.pingsoft.mark.pojo.User_role;
import com.pingsoft.mark.sevice.IUserService;
import com.pingsoft.mark.sevice.IUser_roleService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Resource
    private IUser_roleService user_roleService;

    @Override
    public User selectById(Long id) {


        return getBaseMapper().selectById(id);
    }

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(JSONObject jsonObject) {
        String account = jsonObject.getString("account");
        String username = jsonObject.getString("username");
        String phone = jsonObject.getString("phone");
        String password = jsonObject.getString("password");
        ArrayList<Integer> roles = (ArrayList<Integer>) jsonObject.get("roles");
        User user = new User();
        user.setAccount(account.trim());
        user.setCreate_time(LocalDateTime.now());
        user.setDiscard(Constant.NOT_DELETE);
        user.setEnabled(true);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setTelephone(phone);
        user.setUsername(username);
        getBaseMapper().insert(user);

        if (!roles.isEmpty()) {
            Long userid = user.getId();
            List<User_role> user_roleList = new ArrayList<>();
            roles.stream().forEach(r -> {
                User_role ur = new User_role();
                ur.setRid(Long.valueOf(r));
                ur.setUid(userid);
                user_roleList.add(ur);
            });
            user_roleService.saveBatch(user_roleList);
        }

    }

    @Override
    public void update(String id, JSONObject jsonObject) {
        String account = jsonObject.getString("account");
        String username = jsonObject.getString("username");
        String phone = jsonObject.getString("phone");
        String password = jsonObject.getString("password");
        ArrayList<Integer> roles = (ArrayList<Integer>) jsonObject.get("roles");

        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.set("account", account.trim()).set("username", username).set("telephone", phone)
                .eq("id", Long.valueOf(id));
        if (!StringUtils.isEmpty(password)) {
            userUpdateWrapper.set("password", new BCryptPasswordEncoder().encode(password));
        }
        update(userUpdateWrapper);

        QueryWrapper<User_role> user_roleQueryWrapper = new QueryWrapper<>();
        user_roleQueryWrapper.eq("uid", Long.valueOf(id));
        user_roleService.getBaseMapper().delete(user_roleQueryWrapper);

        if (!roles.isEmpty()) {
            List<User_role> user_roleList = new ArrayList<>();
            roles.stream().forEach(r -> {
                User_role ur = new User_role();
                ur.setRid(Long.valueOf(r));
                ur.setUid(Long.valueOf(id));
                user_roleList.add(ur);
            });
            user_roleService.saveBatch(user_roleList);
        }

    }

    @Override
    public Integer selectCountByAccount(String account, Long userId) {
        return getBaseMapper().selectCountByAccount(account, userId);
    }

    @Override
    public IPage<User> page(String name, Integer currentPage) {
        return getBaseMapper().pageUser(new Page<>(currentPage, Constant.PAGE_SIZE), name);
    }

    @Override
    public void deleteById(Long id) {
        getBaseMapper().deleteById(id);
    }
}
