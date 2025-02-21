package com.example.demo.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PageableUtil {

    private static final String DEFAULT_PAGE = "0";
    private static final String DEFAULT_SIZE = "10";

    public static String convertPageableToString(Pageable pageable) {
        if (pageable == null) {
            return "page=" + DEFAULT_PAGE + "&size=" + DEFAULT_SIZE;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("size=").append(pageable.getPageSize());
        sb.append("&page=").append(pageable.getPageNumber());

        if (pageable.getSort().isSorted()) {
            sb.append("&sort=");
            pageable.getSort().forEach(order ->
                    sb.append(order.getProperty()).append(",").append(order.getDirection()).append(","));
            sb.deleteCharAt(sb.length() - 1); // Xóa dấu `,` cuối cùng
        }
        return sb.toString();
    }

    public static Pageable parsePageable(String pageableStr) {

        if (pageableStr == null || pageableStr.isBlank()) {
            return PageRequest.of(Integer.parseInt(DEFAULT_PAGE), Integer.parseInt(DEFAULT_SIZE));
        }

        String[] params = pageableStr.split("&");
        int page = Integer.parseInt(DEFAULT_PAGE);
        int size = Integer.parseInt(DEFAULT_SIZE);
        Sort sort = Sort.unsorted();

        for (String param : params) {
            String[] keyValue = param.split("=");
            if (keyValue.length == 2) {
                switch (keyValue[0]) {
                    case "page" -> page = Integer.parseInt(keyValue[1]);
                    case "size" -> size = Integer.parseInt(keyValue[1]);
                    case "sort" -> sort = parseSort(keyValue[1]);
                }
            }
        }

        return PageRequest.of(page, size, sort);
    }

    private static Sort parseSort(String sortStr) {
        if (sortStr == null || sortStr.isBlank()) {
            return Sort.unsorted();
        }
        List<Sort.Order> orders = Arrays.stream(sortStr.split(","))
                .map(s -> {
                    String[] parts = s.split(",");
                    return new Sort.Order(Sort.Direction.fromString(parts[1]), parts[0]);
                })
                .collect(Collectors.toList());
        return Sort.by(orders);
    }

}
