package br.com.carlos.Simple_E_Commerce.service;

import br.com.carlos.Simple_E_Commerce.dto.CategoryDto;
import br.com.carlos.Simple_E_Commerce.entity.CategoryEntity;
import br.com.carlos.Simple_E_Commerce.exception.CategoryAlreadyExists;
import br.com.carlos.Simple_E_Commerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryEntity addCategory(CategoryDto dto) {

        categoryRepository.findByCategoryName(dto.name()).ifPresent(present -> {
            throw new CategoryAlreadyExists();
        });

        var newCategory = new CategoryEntity();
        newCategory.setCategoryName(dto.name());
        return categoryRepository.save(newCategory);

    }
}
