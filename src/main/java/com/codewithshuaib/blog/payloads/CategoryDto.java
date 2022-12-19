package com.codewithshuaib.blog.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryDto {
    private String categoryId;
    @NotEmpty
    @Size(min = 4,message = "Min size of category title is 4")
    private String categoryTitle;
    @NotEmpty
    @Size(min=10,message = "min size of category description is 10")
    private String categoryDescription;
}
