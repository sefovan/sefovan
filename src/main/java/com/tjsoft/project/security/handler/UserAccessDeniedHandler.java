package com.tjsoft.project.security.handler;

import com.tjsoft.project.response.ResponseEnum;
import com.tjsoft.project.response.ResponseValue;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 拒绝访问处理器
 * @author sefo
 * @version V1.0
 * @Package com.fjm.project.handler
 * @date 2021/7/27 11:23
 * @Copyright ©
 */
@Component
public class UserAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        ResponseValue.responseJson(httpServletResponse, ResponseValue.response(ResponseEnum.REJECT_LOGIN.getCode(), ResponseEnum.REJECT_LOGIN.getMsg(), e.getMessage()));
    }
}
