package com.example.demo.configuration;

import com.example.demo.security.CustomUserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class FeignClientInterceptor implements RequestInterceptor {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void apply(RequestTemplate requestTemplate) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
            try {
                String userDetailsJson = objectMapper.writeValueAsString(userDetails);
                requestTemplate.header("X-User-Details", userDetailsJson);
            } catch (Exception e) {
                throw new RuntimeException("Failed to serialize CustomUserDetails", e);
            }
        }
    }
}
