package com.carrental.config;

import com.carrental.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.
        UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.
        SecurityContextHolder;
import org.springframework.security.web.authentication.
        WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.authority.
        SimpleGrantedAuthority;

import java.util.List;
import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter
        extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthenticationFilter(
            JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println("JWT Filter triggered");

        String authHeader =
                request.getHeader("Authorization");

        if (authHeader == null ||
                !authHeader.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);
            return;
        }

        String token =
                authHeader.substring(7);

        boolean valid =
                jwtService.isTokenValid(token);

        System.out.println("TOKEN VALID: " + valid);

        if (valid) {

            Long userId =
                    jwtService.extractUserId(token);

            String role =
                    jwtService.extractRole(token);
            System.out.println("ROLE FROM TOKEN: " + role);

            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(
                            userId,
                            null,
                            List.of(
                                    new SimpleGrantedAuthority(
                                            "ROLE_" + role
                                    )
                            )
                    );

            SecurityContextHolder
                    .getContext()
                    .setAuthentication(auth);

            System.out.println("SETTING AUTH FOR USER: " + userId);
        }

        filterChain.doFilter(request, response);
    }
}