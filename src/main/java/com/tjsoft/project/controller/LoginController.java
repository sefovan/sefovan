package com.tjsoft.project.controller;

import com.tjsoft.project.core.ProjectConstants;
import com.tjsoft.project.security.SysUserDetails;
import com.tjsoft.project.util.JWTTokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author sefo
 * @version V1.0
 * @Package com.tjsoft.project.controller
 * @date 2021/8/18 16:05
 * @Copyright ©
 */
@Api(tags = {"登陆控制器"})
@RestController
public class LoginController {

    @Resource
    private UserDetailsService userDetailsService;

    @ApiOperation(value = "登陆接口")
    @PostMapping("/login")
    @ApiImplicitParams({
            @ApiImplicitParam(name="username",value="用户名",required=true,paramType="form"),
            @ApiImplicitParam(name="password",value="密码",required=true,paramType="form")
    })
    public String login(@RequestParam String username, @RequestParam String password){
        // 登陆验证
        SysUserDetails sysUserDetails = (SysUserDetails) userDetailsService.loadUserByUsername(username);
        if (sysUserDetails == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        if (!new BCryptPasswordEncoder().matches(password, sysUserDetails.getPassword())) {
            throw new BadCredentialsException("用户名或密码错误");
        }

        if (sysUserDetails.getStatus().equals(ProjectConstants.USER_STATUS_2)) {
            throw new LockedException("用户已禁用");
        }

        // 登陆验证
        return JWTTokenUtils.createAccessToken(sysUserDetails);
    }
}
