package com.tjsoft.project.security.handler;

import com.tjsoft.project.response.ResponseEnum;
import com.tjsoft.project.response.ResponseValue;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 未登录处理器
 * @author sefo
 * @version V1.0
 * @Package com.fjm.project.handler
 * @date 2021/7/27 11:27
 * @Copyright ©
 */
@Component
public class UserNotLoginHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResponseValue.responseJson(httpServletResponse, ResponseValue.response(ResponseEnum.NO_LOGIN.getCode(), ResponseEnum.NO_LOGIN.getMsg(), e.getMessage()));
    }
}
