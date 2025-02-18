package com.example.demo.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Collections;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageResponseDto<T> {
    int currentPage;
    int totalPages;
    int pageSize;
    long totalElements;

    @Builder.Default
    private List<T> data = Collections.emptyList();
}
