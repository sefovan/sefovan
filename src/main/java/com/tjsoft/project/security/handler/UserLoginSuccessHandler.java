package com.tjsoft.project.security.handler;

import com.tjsoft.project.response.ResponseEnum;
import com.tjsoft.project.response.ResponseValue;
import com.tjsoft.project.security.SysUserDetails;
import com.tjsoft.project.util.AccessAddressUtils;
import com.tjsoft.project.util.JWTTokenUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登陆成功处理器
 * @author sefo
 * @version V1.0
 * @Package com.fjm.project.handler
 * @date 2021/7/27 11:31
 * @Copyright ©
 */
@Log4j2
@Component
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
//        SysUserDetails sysUserDetails = (SysUserDetails) authentication.getPrincipal();
//        String token = JWTTokenUtil.createAccessToken(sysUserDetails);
//        Map<String, String> tokenMap = new HashMap<>();
//        tokenMap.put("token", token);
        SysUserDetails sysUserDetails = (SysUserDetails) authentication.getPrincipal();
        // 获得请求IP
        String ip = AccessAddressUtils.getIpAddress(httpServletRequest);
        sysUserDetails.setIp(ip);
        String token = JWTTokenUtils.createAccessToken(sysUserDetails);

        // 保存Token信息到Redis中
        JWTTokenUtils.setTokenInfo(token, sysUserDetails.getUsername(), ip);

        log.info("用户{}登录成功，Token信息已保存到Redis", sysUserDetails.getUsername());

        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        ResponseValue.responseJson(httpServletResponse, ResponseValue.response(ResponseEnum.SUCCESS_LOGIN.getCode(), ResponseEnum.SUCCESS_LOGIN.getMsg(), tokenMap));
    }
}
