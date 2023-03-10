package com.codewithshuaib.blog.services.impl;

import com.codewithshuaib.blog.entities.Category;
import com.codewithshuaib.blog.exceptions.ResourceNotFoundException;
import com.codewithshuaib.blog.payloads.CategoryDto;
import com.codewithshuaib.blog.repositories.CategoryRepository;
import com.codewithshuaib.blog.services.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
@Service @RequiredArgsConstructor @Transactional @Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category=this.modelMapper.map(categoryDto,Category.class);
        Category addedCategory=this.categoryRepository.save(category);
        return this.modelMapper.map(addedCategory,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
        Category category=this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category id",categoryId+""));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        Category updatedCategory=this.categoryRepository.save(category);
        return this.modelMapper.map(updatedCategory,CategoryDto.class);
    }

    @Override
    public CategoryDto getSingleCategory(Long categoryId) {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id",categoryId+""));
        return this.modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id",categoryId+""));
        this.categoryRepository.delete(category);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories=this.categoryRepository.findAll();
       List<CategoryDto>categoryDtos= categories.stream().map((category)->this.modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());
        return categoryDtos;
    }

}
