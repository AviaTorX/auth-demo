package org.mine.authdemo.security.jwt;

import jakarta.servlet.http.HttpServletRequest;
import org.mine.authdemo.users.dtos.UserResponseDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationConverter;

import java.util.Collection;
import java.util.List;

public class JwtAuthenticationConverter implements AuthenticationConverter {


    @Override
    public Authentication convert(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            var token = authHeader.substring(7);
            return new JwtAuthentication(token);
        }

        return null;
    }
}
