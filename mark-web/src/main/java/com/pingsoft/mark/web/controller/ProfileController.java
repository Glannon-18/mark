package com.pingsoft.mark.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.pingsoft.mark.pojo.Menu;
import com.pingsoft.mark.pojo.RespBean;
import com.pingsoft.mark.pojo.Role;
import com.pingsoft.mark.pojo.User;
import com.pingsoft.mark.sevice.IMenuService;
import com.pingsoft.mark.sevice.IRoleService;
import com.pingsoft.mark.sevice.IUserService;
import com.pingsoft.mark.web.util.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/profile")
public class ProfileController {


    @Value("${system.upload.temp_dir}")
    private String temp_dir;

    @Resource
    private IUserService userService;

    @Resource
    private IRoleService roleService;

    @Resource
    private IMenuService menuService;


    @GetMapping("/info")
    public RespBean info() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.select("username", "photo").eq("id", user.getId());
        User user1 = userService.getOne(userQueryWrapper);
        List<Role> roles = roleService.selectByUserId(user.getId());
        user1.setRoleList(roles);
        return RespBean.ok(user1);
    }

    @GetMapping("/menu")
    public RespBean menu() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Menu> menuList = menuService.getMenuSideBar(user.getId());
        return RespBean.ok(menuList);
    }

    @PostMapping("/upload_img")
    public RespBean upload_img(MultipartFile file) throws IOException {
        String extend = file.getOriginalFilename().split("\\.")[1];
        String upload_name = UUID.randomUUID().toString() + "." + extend;
        String path = temp_dir + File.separator + upload_name;
        File upload_file = new File(path);
        if (!upload_file.getParentFile().exists()) {
            upload_file.getParentFile().mkdirs();
        }
        file.transferTo(upload_file);
        return RespBean.ok(upload_name);
    }

    @PostMapping("/setAvatar")
    public RespBean setAvatar(@RequestBody JSONObject data) {
        String name = UUID.randomUUID().toString() + ".jpg";
        String filePath = temp_dir;
        Base64.convertBase64ToFile(data.getString("base64"), filePath, name);
        HashMap<String, String> result = new HashMap<>();
        result.put("name", name);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.set("photo", name).eq("id", user.getId());
        userService.update(userUpdateWrapper);
        return RespBean.ok(result);
    }

    @PutMapping("/setUsername")
    public RespBean setName(@RequestParam String username) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.set("username", username).eq("id", user.getId());
        userService.update(userUpdateWrapper);
        HashMap<String, String> result = new HashMap<>();
        result.put("updateName", username);
        return RespBean.ok("修改用户名成功！", result);
    }

}
