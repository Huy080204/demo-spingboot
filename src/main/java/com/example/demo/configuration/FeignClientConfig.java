package com.example.demo.configuration;

import com.example.demo.exception.CustomFeignErrorDecoder;
import com.example.demo.util.PageableUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.stream.Collectors;

public class FeignClientConfig {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String PAGEABLE_HEADER = "CUSTOM_PAGEABLE";

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomFeignErrorDecoder();
    }

    @Bean
    public RequestInterceptor customInterceptor() {
        return new RequestInterceptor() {

            @Override
            public void apply(RequestTemplate template) {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                if (authentication != null && authentication.getCredentials() instanceof String token) {
                    template.header(AUTHORIZATION_HEADER, "Bearer " + token);
                }

                if (template.headers().containsKey(PAGEABLE_HEADER)) {
                    List<String> values = template.headers().get(PAGEABLE_HEADER).stream().toList();
                    if (!values.isEmpty()) {
                        String pageableStr = values.getFirst();
                        Pageable pageable = PageableUtil.parsePageable(pageableStr);

                        template.query("size", String.valueOf(pageable.getPageSize()));
                        template.query("page", String.valueOf(pageable.getPageNumber()));

                        if (pageable.getSort().isSorted()) {
                            String sortParam = pageable.getSort().stream()
                                    .map(order -> order.getProperty() + "," + order.getDirection())
                                    .collect(Collectors.joining(","));
                            template.query("sort", sortParam);
                        }
                    }

                    template.headers().remove(PAGEABLE_HEADER);
                }
            }
        };
    }
}
