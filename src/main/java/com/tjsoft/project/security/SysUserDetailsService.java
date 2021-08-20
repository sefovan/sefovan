package com.tjsoft.project.security;

import com.tjsoft.project.entity.SysRole;
import com.tjsoft.project.entity.SysUser;
import com.tjsoft.project.service.SysRoleService;
import com.tjsoft.project.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户登录Service
 * @author sefo
 * @version V1.0
 * @Package com.tjsoft.project.security
 * @date 2021/8/12 16:54
 * @Copyright ©
 */
@Service
public class SysUserDetailsService implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 根据用户名查用户信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.findUserByUserName(username);
        if (sysUser != null) {
            SysUserDetails sysUserDetails = new SysUserDetails();
            BeanUtils.copyProperties(sysUser, sysUserDetails);

            Set<GrantedAuthority> authorities = new HashSet<>(); // 角色集合

            List<SysRole> roleList = sysRoleService.findRoleByUserId(sysUserDetails.getId());
            roleList.forEach(role -> {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
            });

            sysUserDetails.setAuthorities(authorities);

            return sysUserDetails;
        }
        return null;
    }
}
