package br.com.carlos.Simple_E_Commerce.controller;

import br.com.carlos.Simple_E_Commerce.dto.ProductDto;
import br.com.carlos.Simple_E_Commerce.dto.ProductResponseDTO;
import br.com.carlos.Simple_E_Commerce.entity.ProductEntity;
import br.com.carlos.Simple_E_Commerce.service.ProductService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping
    public ResponseEntity<ProductResponseDTO> getProductDetails(String productId){

    }

}
