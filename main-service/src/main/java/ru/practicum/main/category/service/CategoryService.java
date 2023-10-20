package ru.practicum.main.category.service;

import ru.practicum.main.category.dto.CategoryDto;
import ru.practicum.main.category.dto.NewCategoryDto;

import java.util.Collection;

public interface CategoryService {
    CategoryDto addCategoryAdmin(NewCategoryDto newCategoryDto);

    CategoryDto updateCategoryAdmin(long categoryId, CategoryDto categoryDto);

    void deleteCategoryByIdAdmin(Long categoryId);

    Collection<CategoryDto> getAllCategoriesPub(int from, int size);

    CategoryDto getCategoryByIdPub(Long categoryId);
}
