package com.pingsoft.mark.web;

import com.pingsoft.mark.pojo.Menu;
import com.pingsoft.mark.sevice.IMenuService;
import com.pingsoft.mark.sevice.IRoleService;
import com.pingsoft.mark.sevice.IUserService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class MarkWebApplicationTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(MarkWebApplicationTests.class);

    @Resource
    private IUserService iUserService;

    @Resource
    private IMenuService iMenuService;

    @Resource
    private IRoleService roleService;

    @Test
    void contextLoads() {
    }

    @Test
    void test0() {
        iUserService.selectUserWithRolesByAccount("admin");
    }


    @Test
    void test1() {
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }

    @Test
    void test2() {
        List<Menu> menuList = iMenuService.getAllMenusWithRole();
        LOGGER.info(menuList.toString());

    }

    @Test
    void test3() {
        List<Menu> menuList = iMenuService.getMenuSideBar(1l);
        LOGGER.info(menuList.toString());

    }

    @Test
    void test4() {
        roleService.selectByUserId(1l);
    }

    @Test
    void test5() {
        List<Menu> menuTree = iMenuService.getMenuTree();
        LOGGER.info(menuTree.toString());
    }
}

