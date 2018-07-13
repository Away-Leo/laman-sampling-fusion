package com.laman.web.common.security.moduls;

import com.laman.biz.user.app.dto.UserDto;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;

public class CusAuthToken extends AbstractAuthenticationToken{

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private final Object principal;
    private Object credentials;

    private UserDto userDto;


    public CusAuthToken(Object principal, Object credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(false);
    }

    public CusAuthToken(UserDto userDto) {
        super(null);
        this.principal = userDto.getUserName();
        this.credentials = userDto.getPassword();
        this.userDto=userDto;
        setAuthenticated(false);
    }
    public CusAuthToken(UserDto userDto,Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = userDto.getUserName();
        this.credentials = userDto.getPassword();
        this.userDto=userDto;
        super.setAuthenticated(true);
    }

    public CusAuthToken(Object principal, Object credentials,
                                               Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true); // must use super, as we override
    }

    // ~ Methods
    // ========================================================================================================


    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public Object getCredentials() {
        return this.credentials;
    }

    public Object getPrincipal() {
        return this.principal;
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }

        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        credentials = null;
    }
}
