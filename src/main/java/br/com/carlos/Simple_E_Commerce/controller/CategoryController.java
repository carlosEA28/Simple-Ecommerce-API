package br.com.carlos.Simple_E_Commerce.controller;

import br.com.carlos.Simple_E_Commerce.dto.CategoryDto;
import br.com.carlos.Simple_E_Commerce.entity.CategoryEntity;
import br.com.carlos.Simple_E_Commerce.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryEntity> addCategory(@Valid @RequestBody CategoryDto dto) {
        var category = categoryService.addCategory(dto);

        return ResponseEntity.ok().body(category);
    }
}
