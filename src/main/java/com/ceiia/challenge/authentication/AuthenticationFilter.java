package com.ceiia.challenge.authentication;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Value("${api.key1}")
    private String apiKey1;

    @Value("${api.key2}")
    private String apiKey2;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Get the API key from request headers
        String requestApiKey = ((HttpServletRequest)request).getHeader("X-API-KEY");
        // Validate the key
        if (apiKey1.equals(requestApiKey) || apiKey2.equals(requestApiKey)) {
            // Continue processing the request
            filterChain.doFilter(request, response);
        } else {
            // Reject the request and send an unauthorized error
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        }
    }
}
