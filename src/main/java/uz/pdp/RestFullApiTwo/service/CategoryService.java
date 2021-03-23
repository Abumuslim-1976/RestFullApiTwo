package uz.pdp.RestFullApiTwo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.RestFullApiTwo.Entity.Category;
import uz.pdp.RestFullApiTwo.Entity.Language;
import uz.pdp.RestFullApiTwo.payload.ApiResponse;
import uz.pdp.RestFullApiTwo.payload.CategoryDto;
import uz.pdp.RestFullApiTwo.repository.CategoryRepository;
import uz.pdp.RestFullApiTwo.repository.LanguageRepository;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    LanguageRepository languageRepository;


    /**
     * Category created
     *
     * @param categoryDto
     * @return
     */
    public ApiResponse createCategory(CategoryDto categoryDto) {

        List<Language> languages = languageRepository.findAllById(categoryDto.getLanguageId());

        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setLanguage(languages);
        categoryRepository.save(category);
        return new ApiResponse("Category created", true);
    }


    /**
     * get All Categories
     *
     * @return
     */
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }


    /**
     * get Category
     *
     * @param id
     * @return
     */
    public Category getCategory(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElseGet(Category::new);
    }


    /**
     * category deleted
     *
     * @param id
     * @return
     */
    public ApiResponse deleteCategory(Integer id) {
        try {
            categoryRepository.deleteById(id);
        } catch (Exception e) {
            return new ApiResponse("ERROR !!! , category not found", false);
        }
        return new ApiResponse("category deleted", true);
    }


    /**
     * category edited
     * @param id
     * @param categoryDto
     * @return
     */
    public ApiResponse editCategory(Integer id, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent())
            return new ApiResponse("category not found", false);

        List<Language> languages = languageRepository.findAllById(categoryDto.getLanguageId());

        Category category = optionalCategory.get();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setLanguage(languages);
        categoryRepository.save(category);
        return new ApiResponse("category edited", true);
    }



}


