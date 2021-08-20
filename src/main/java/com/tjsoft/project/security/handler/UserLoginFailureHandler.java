package com.tjsoft.project.security.handler;

import com.tjsoft.project.response.ResponseEnum;
import com.tjsoft.project.response.ResponseValue;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登陆失败处理器
 * @author sefo
 * @version V1.0
 * @Package com.fjm.project.handler
 * @date 2021/7/27 11:32
 * @Copyright ©
 */
@Component
public class UserLoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResponseValue.responseJson(httpServletResponse, ResponseValue.response(ResponseEnum.FAIL_LOGIN.getCode(), ResponseEnum.FAIL_LOGIN.getMsg(), e.getMessage()));

    }
}
