package com.codewithshuaib.blog.payloads;

import lombok.Data;

import java.util.List;
@Data
public class PostResponse {
    private List<PostDto> content;
    private int pageNumber;
    private long totalElements;
    private int pageSize;
    private int totalPages;
    private boolean lastPage;
}
