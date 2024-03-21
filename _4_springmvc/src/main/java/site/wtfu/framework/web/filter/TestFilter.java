package site.wtfu.framework.web.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/3/21
 *                          @since  1.0
 *                          @author 12302
 *
 */
@Component
//@WebFilter
@WebFilter(urlPatterns = "/*", initParams = {@WebInitParam(name = "allowedMethods", value = "GET,POST")})
public class TestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        System.out.println("pass by filter");
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}