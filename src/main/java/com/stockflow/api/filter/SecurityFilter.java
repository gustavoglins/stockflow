package com.stockflow.api.filter;

import com.stockflow.domain.repositories.UserRepository;
import com.stockflow.domain.services.impl.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRepository userRepository;

    public SecurityFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getServletPath();
        if (path.equals("/api/auth/login/user") || path.equals("/api/auth/signup/user")) {
            filterChain.doFilter(request, response);
            return;
        }
        var token = this.recoverToken(request);
        if (token != null) {
            var subjectLogin = tokenService.validateToken(token);
            UserDetails user = userRepository.findByLogin(subjectLogin);

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        var token = this.recoverToken(request);
//        if (token != null) {
//            var subjectLogin = tokenService.validateToken(token);
//            UserDetails user = userRepository.findByLogin(subjectLogin);
//
//            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }
//        filterChain.doFilter(request, response);
//    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
