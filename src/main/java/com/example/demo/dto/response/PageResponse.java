package com.example.demo.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Collections;
import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageResponse<T> {
    int currentPage;
    int totalPages;
    int pageSize;
    long totalElements;

    @Builder.Default
    private List<T> data = Collections.emptyList();
}
