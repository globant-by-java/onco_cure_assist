package com.globant.internal.oncocureassist.endpoint;

import java.util.List;

class PageResponse<T> {

    private final long totalPages;
    private final long totalElements;
    private final List<T> content;


    PageResponse(int totalPages, long totalElements, List<T> content) {
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.content = content;
    }


    public long getTotalPages() {
        return totalPages;
    }


    public long getTotalElements() {
        return totalElements;
    }


    public List<T> getContent() {
        return content;
    }
}
