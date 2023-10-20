package ru.practicum.main.category.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.main.category.dto.CategoryDto;
import ru.practicum.main.category.dto.NewCategoryDto;
import ru.practicum.main.category.dto.mapper.CategoryMapper;
import ru.practicum.main.category.model.Category;
import ru.practicum.main.category.repository.CategoryRepository;
import ru.practicum.main.exception.NotFoundException;
import ru.practicum.main.utils.PageRequestUtil;

import java.util.Collection;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Transactional
    @Override
    public CategoryDto addCategoryAdmin(NewCategoryDto newCategoryDto) {
        Category newCategory = categoryMapper.toCategory(newCategoryDto);
        Category addedCategory = categoryRepository.save(newCategory);
        log.info("Admin added category={}", addedCategory);
        return categoryMapper.toCategoryDto(addedCategory);
    }

    @Transactional
    @Override
    public CategoryDto updateCategoryAdmin(long categoryId, CategoryDto categoryDto) {
        Category oldCategory = findCategoryById(categoryId);
        Category newCategory = categoryMapper.toCategory(categoryDto);
        newCategory.setId(categoryId);
        Category updatedCategory = categoryRepository.save(newCategory);
        log.info("Admin updated old category={} to new category={}", oldCategory, updatedCategory);
        return categoryMapper.toCategoryDto(updatedCategory);
    }

    @Transactional
    @Override
    public void deleteCategoryByIdAdmin(Long categoryId) {
        findCategoryById(categoryId);
        categoryRepository.deleteById(categoryId);
        log.info("Admin deleted category with id={}", categoryId);
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<CategoryDto> getAllCategoriesPub(int from, int size) {
        List<Category> allCategories = categoryRepository.findAll(PageRequestUtil.of(from, size)).toList();
        log.info("Returned all {} categories", allCategories.size());
        return categoryMapper.toCategoryList(allCategories);
    }

    @Transactional(readOnly = true)
    @Override
    public CategoryDto getCategoryByIdPub(Long categoryId) {
        Category category = findCategoryById(categoryId);
        log.info("Returned category with id={}", categoryId);
        return categoryMapper.toCategoryDto(category);
    }

    private Category findCategoryById(long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() ->
                new NotFoundException(String.format("Category with id=%d was not found", categoryId)));
    }
}
