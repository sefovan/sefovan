package com.tjsoft.project.security;

import com.tjsoft.project.entity.SysAuth;
import com.tjsoft.project.service.SysAuthService;
import com.tjsoft.project.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户权限注解处理类
 *
 * @author sefo
 * @version V1.0
 * @Package com.tjsoft.project.security
 * @date 2021/8/12 17:10
 * @Copyright ©
 */
@Component
public class UserPermissionEvaluator implements PermissionEvaluator {
    @Autowired
    private SysAuthService sysAuthService;

    /**
     * 判断是否拥有权限
     *
     * @param authentication 用户身份
     * @param targetUrl      目标路径
     * @param permission     路径权限
     * @return 是否拥有权限
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object targetUrl, Object permission) {
        SysUserDetails sysUserDetails = (SysUserDetails) authentication.getPrincipal();
        // 用户权限
        Set<String> permissions = new HashSet<String>();

//        List<SysAuth> authList = sysAuthService.findAuthByUserId(sysUserDetails.getId());
        sysAuthService.findAuthByUserId(sysUserDetails.getId()).forEach(auth -> {
            permissions.add(auth.getPermission());
        });

        // 判断是否拥有权限
        return permissions.contains(permission.toString());
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
                                 Object permission) {
        return false;
    }
}
