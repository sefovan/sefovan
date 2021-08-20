package com.tjsoft.project.security;

import com.tjsoft.project.entity.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author sefo
 * @version V1.0
 * @Package com.fjm.project.security
 * @date 2021/7/27 11:05
 * @Copyright ©
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserDetails extends SysUser implements UserDetails, Serializable {
    /**
     * 用户角色
     */
    private Collection<GrantedAuthority> authorities;
    /**
     * 账号是否过期
     */
    private boolean isAccountNonExpired = false;
    /**
     * 账号是否锁定
     */
    private boolean isAccountNonLocked = false;
    /**
     * 证书是否过期
     */
    private boolean isCredentialsNonExpired = false;
    /**
     * 账号是否有效
     */
    private boolean isEnabled = true;

    private String ip;

}
