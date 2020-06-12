package com.pingsoft.mark.web;

import com.pingsoft.mark.sevice.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class MarkWebApplicationTests {

    @Resource
    private IUserService iUserService;

    @Test
    void contextLoads() {
    }

    @Test
     void test0(){
        iUserService.getById(3);
    }


}
