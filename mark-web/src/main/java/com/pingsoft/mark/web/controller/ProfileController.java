package com.pingsoft.mark.web.controller;

import com.pingsoft.mark.pojo.RespBean;
import com.pingsoft.mark.sevice.IUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/profile")
public class ProfileController {


    @Value("${system.upload.temp_dir}")
    private String temp_dir;

    @Resource
    private IUserService userService;


    @PostMapping("upload_img")
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

}
