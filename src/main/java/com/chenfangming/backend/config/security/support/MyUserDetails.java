package com.chenfangming.backend.config.security.support;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * MyUserDetails
 * @author 陈方明  cfmmail@sina.com
 * @since 2019-01-01 14:58
 */
public class MyUserDetails implements UserDetails {
    /** 用户id **/
    private long id;
    /** 用户名  忽略大小写 **/
    private String username;
    /** 密码  区分大小写 **/
    @JsonIgnore
    private String password;
    /** 是否可用  0不可用  1可用 **/
    @JsonIgnore
    private boolean enabled;
    /** 角色id集合 **/
    private List<SimpleGrantedAuthority> authorities;

    public MyUserDetails() {
    }

    public MyUserDetails(long id, String username, String password, boolean enabled, List<SimpleGrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public List<SimpleGrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setAuthorities(List<SimpleGrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
