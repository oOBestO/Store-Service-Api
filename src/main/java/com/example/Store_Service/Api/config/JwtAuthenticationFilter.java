package com.example.Store_Service.Api.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.Store_Service.Api.service.JwtService;
import com.example.Store_Service.Api.service.UserService;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ApplicationContext context;

    private UserService userService;

    @Override
protected void doFilterInternal(HttpServletRequest request,
                                HttpServletResponse response,
                                FilterChain filterChain) throws ServletException, IOException {

    String path = request.getRequestURI();

    // ✅ Skip ตรวจสอบ token สำหรับ path ที่ไม่ต้อง login
    if (path.startsWith("/api/orders")
        || path.startsWith("/api/auth")
        || path.startsWith("/api/menus")
        || path.startsWith("/api/images")
        || path.startsWith("/api/tables")
    ) {
        filterChain.doFilter(request, response);
        return;
    }

    // 👇 ของเดิมเริ่มตรงนี้
    if (userService == null) {
        userService = context.getBean(UserService.class);
    }

    final String authHeader = request.getHeader("Authorization");
    final String jwtToken;
    final String username;

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        filterChain.doFilter(request, response);
        return;
    }

    jwtToken = authHeader.substring(7);
    username = jwtService.extractUsername(jwtToken);

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails userDetails = userService.loadUserByUsername(username);

        if (jwtService.isTokenValid(jwtToken, userDetails)) {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
    }

    filterChain.doFilter(request, response);
}
}