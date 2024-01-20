package com.vivek.commerce.product.category;

import com.vivek.commerce.exception.NotFoundException;
import com.vivek.commerce.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommerceCategoryService implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        return saveCategoryRecursively(categoryDTO, null);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(category -> getCategoryByName(category.getName())).toList();
    }

    @Override
    public CategoryDTO getCategoryByName(String categoryName) {
        Category category = categoryRepository.findById(categoryName).orElse(null);
        return convertToDTOWithHierarchy(category);
    }

    @Override
    public CategoryDTO updateCategory(String categoryName, CategoryDTO categoryDTO) {
        Category existingCategory = getCategoryByNameOrThrow(categoryName);
        Category updatedCategory = categoryRepository.save(existingCategory);
        return convertToDTO(updatedCategory);
    }

    @Override
    public void deleteCategory(String categoryName) {
        Category category = getCategoryByNameOrThrow(categoryName);
        Category parentCategory = categoryRepository.findByParentCategory(category);
        if(parentCategory != null){
            throw new ServiceException("cannot delete category as not a top level category, try -> %s ".formatted(parentCategory.getName(),"NOT A TOP LEVEL CATEGORY"));
        }
        categoryRepository.delete(category);
    }



    private CategoryDTO convertToDTOWithHierarchy(Category category) {
        if (category == null) {
            return null;
        }
        CategoryDTO categoryDTO = convertToDTO(category);
        categoryDTO.setParentCategory(convertToDTOWithHierarchy(category.getParentCategory()));
        return categoryDTO;
    }


    private Category getCategoryByNameOrThrow(String categoryName) {
        if (categoryName.isBlank()) {
            throw new ServiceException("category name cannot be blank","INVALID INPUT");
        }
        return categoryRepository.findById(categoryName).orElseThrow(() -> new NotFoundException("Category not found with name: " + categoryName));
    }


    private CategoryDTO saveCategoryRecursively(CategoryDTO categoryDTO, Category parentCategory) {
        Category category = convertToEntity(categoryDTO);
        category.setParentCategory(parentCategory);

        category = categoryRepository.save(category);
        CategoryDTO savedCategoryDTO = convertToDTO(category);

        if (categoryDTO.getParentCategory() != null) {
            savedCategoryDTO.setParentCategory(saveCategoryRecursively(categoryDTO.getParentCategory(), category));
        }

        return savedCategoryDTO;
    }

    private Category convertToEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        return category;
    }

    private CategoryDTO convertToDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(category.getName());
        return categoryDTO;
    }
}
