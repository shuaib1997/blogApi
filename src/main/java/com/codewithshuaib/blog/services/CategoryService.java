package com.codewithshuaib.blog.services;

import com.codewithshuaib.blog.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto,Long categoryId);
    CategoryDto getSingleCategory(Long categoryId);
    void deleteCategory(Long categoryId);
    List<CategoryDto> getAllCategory();
}
