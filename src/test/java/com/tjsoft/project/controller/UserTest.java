package com.tjsoft.project.controller;

import com.tjsoft.project.ProjectApplication;
import com.tjsoft.project.entity.SysUser;
import com.tjsoft.project.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author sefo
 * @version V1.0
 * @Package com.tjsoft.project
 * @date 2021/8/11 17:03
 * @Copyright ©
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProjectApplication.class})
public class UserTest {

    @Autowired
    private SysUserService sysUserService;

    @Test
    public void userQueryTest() {
        List<SysUser> sysUsers = sysUserService.queryAllByLimit(0, 5);
        System.out.println(sysUsers);
    }
}
