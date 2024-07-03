package org.mine.authdemo.security.jwt;

import org.apache.catalina.User;
import org.mine.authdemo.users.dtos.UserResponseDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class JwtAuthentication implements Authentication {
    private String jwtString;
    private UserResponseDto user;

    public JwtAuthentication(String jwtString) {
        this.jwtString = jwtString;
    }

    public void setUser(UserResponseDto user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public Object getCredentials() {
        return jwtString;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public UserResponseDto getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return (user != null);
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return "";
    }
}
