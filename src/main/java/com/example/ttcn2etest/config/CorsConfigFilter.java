package com.example.ttcn2etest.config;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class CorsConfigFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "*");

        // Xử lý các yêu cầu preflight
        if ("OPTIONS".equalsIgnoreCase(((jakarta.servlet.http.HttpServletRequest) servletRequest).getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return; // Kết thúc nếu là yêu cầu preflight
        }

        // Tiếp tục xử lý các yêu cầu khác
        chain.doFilter(servletRequest, servletResponse);
    }
}
