package com.tjsoft.project.security.handler;

import com.tjsoft.project.config.JWTConfig;
import com.tjsoft.project.response.ResponseEnum;
import com.tjsoft.project.response.ResponseValue;
import com.tjsoft.project.util.JWTTokenUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登出成功处理器
 * @author sefo
 * @version V1.0
 * @Package com.fjm.project.handler
 * @date 2021/7/27 11:35
 * @Copyright ©
 */
@Log4j2
@Component
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String token = httpServletRequest.getHeader(JWTConfig.tokenHeader);
        // 添加到黑名单
//        JWTTokenUtils.addBlackList(token);

        String userName = JWTTokenUtils.getUserNameByToken(token);

        //在redis中移除该token
        JWTTokenUtils.deleteRedisToken(token);

        log.info("用户{}登出成功，Token信息已从redis中删除", userName);

        SecurityContextHolder.clearContext();
        ResponseValue.responseJson(httpServletResponse, ResponseValue.response(ResponseEnum.SUCCESS_LOGOUT.getCode(), ResponseEnum.SUCCESS_LOGOUT.getMsg(), null));
    }
}
