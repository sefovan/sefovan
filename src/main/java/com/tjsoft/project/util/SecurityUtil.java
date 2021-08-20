package com.tjsoft.project.util;

import com.tjsoft.project.security.SysUserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * springsecurity工具类（获取登陆用户）
 * @author sefo
 * @version V1.0
 * @Package com.tjsoft.project.util
 * @date 2021/8/19 13:33
 * @Copyright ©
 */
public class SecurityUtil {

    /**
     * 获取用户信息
     * @return
     */
    public static SysUserDetails getUserDetail() {
        return (SysUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * 获取用户id
     * @return id
     */
    public static Long getUserId() {
        return getUserDetail().getId();
    }

    /**
     * 获取用户名
     * @return username
     */
    public static String getUsername() {
        return getUserDetail().getUsername();
    }

    /**
     * 获取用户角色集合
     * @return authorities
     */
    public static List<GrantedAuthority> getAuthorities() {
        return new ArrayList<>(getUserDetail().getAuthorities());
    }
}
