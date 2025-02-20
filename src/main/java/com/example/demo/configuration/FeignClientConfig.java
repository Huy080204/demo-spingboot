package com.example.demo.configuration;

import com.example.demo.exception.CustomFeignErrorDecoder;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Pageable;

import java.util.stream.Collectors;

public class FeignClientConfig {
    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomFeignErrorDecoder();
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new FeignClientInterceptor();
    }

    @Bean
    public RequestInterceptor pageableInterceptor() {
        return new RequestInterceptor() {

            @Override
            public void apply(RequestTemplate template) {
                if (template.method().equals("GET") && template.requestBody() == null) {

                    Pageable pageable = (Pageable) template.request().requestTemplate().queries().get("pageable");

                    if (pageable != null) {
                        template.query("page", String.valueOf(pageable.getPageNumber()));
                        template.query("size", String.valueOf(pageable.getPageSize()));

                        if (pageable.getSort().isSorted()) {
                            String sortParam = pageable.getSort().stream()
                                    .map(order -> order.getProperty() + "," + order.getDirection().name().toLowerCase())
                                    .collect(Collectors.joining("&sort="));
                            template.query("sort", sortParam);
                        }
                    }
                }
            }
        };
    }
}
