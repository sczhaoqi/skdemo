package com.sczhaoqi.skdemo.pojo.dto;

import com.sczhaoqi.skdemo.pojo.bo.UserAccountBo;
import com.sczhaoqi.skdemo.pojo.po.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * @author sczhaoqi
 */
public class UserAccountDto
        extends UserAccountBo
        implements UserDetails
{
    private Boolean remember;

    public UserAccountDto()
    {
    }

    public UserAccountDto(User other)
    {
        super(other);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        if(getRoles() != null){
            return getRoles().parallelStream()
                    .flatMap(role -> role.getPrivileges().parallelStream()).map(privilege -> new SimpleGrantedAuthority(privilege.getPrivilegeName()))
                    .collect(Collectors.toList());
        }else {
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return !getExpired();
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return !getLocked();
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return getEnabled();
    }

    public Boolean getRemember()
    {
        return remember;
    }

    public void setRemember(Boolean remember)
    {
        this.remember = remember;
    }
}
