package br.com.carlos.Simple_E_Commerce.controller;

import br.com.carlos.Simple_E_Commerce.dto.ProductDto;
import br.com.carlos.Simple_E_Commerce.dto.ProductResponseDTO;
import br.com.carlos.Simple_E_Commerce.entity.ProductEntity;
import br.com.carlos.Simple_E_Commerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/addProduct")
    public ResponseEntity<ProductEntity> createProduct(
            @RequestPart("dto") @Valid ProductDto dto,
            @RequestPart("imgUrl") MultipartFile imgUrl
    ) {
        var product = productService.createProduct(dto, imgUrl);
        return ResponseEntity.ok(product);
    }

    // para admin
    @GetMapping("/admin/{productId}")
    public ResponseEntity<Optional<ProductEntity>> getProductAllInfo(@PathVariable("productId") String productId) {
        var product = productService.getProductDetailsEntity(productId);

        return ResponseEntity.ok().body(Optional.ofNullable(product));

    }


    //para user
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO> getProductInfo(@PathVariable("productId") String productId) {
        var product = productService.getProductDetail(productId);

        return ResponseEntity.ok().body(product);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductResponseDTO>> getAllProductsByCategory(@PathVariable UUID categoryId) {
        var products = productService.getAllProductsByCategory(categoryId);
        return ResponseEntity.ok().body(products);
    }


}
