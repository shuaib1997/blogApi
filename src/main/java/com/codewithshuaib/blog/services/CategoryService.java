package com.codewithshuaib.blog.services;

import com.codewithshuaib.blog.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto,String categoryId);
    CategoryDto getSingleCategory(String categoryId);
    void deleteCategory(String categoryId);
    List<CategoryDto> getAllCategory();
}
