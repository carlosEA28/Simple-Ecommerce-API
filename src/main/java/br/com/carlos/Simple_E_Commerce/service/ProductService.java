package br.com.carlos.Simple_E_Commerce.service;

import br.com.carlos.Simple_E_Commerce.dto.ProductDto;
import br.com.carlos.Simple_E_Commerce.dto.ProductResponseDTO;
import br.com.carlos.Simple_E_Commerce.entity.CategoryEntity;
import br.com.carlos.Simple_E_Commerce.entity.ProductEntity;
import br.com.carlos.Simple_E_Commerce.exception.ProductAlredyExists;
import br.com.carlos.Simple_E_Commerce.exception.ProductDoesNotExists;
import br.com.carlos.Simple_E_Commerce.repository.CategoryRepository;
import br.com.carlos.Simple_E_Commerce.repository.ProductRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private Cloudinary cloudinary;

    public ProductEntity createProduct(ProductDto dto, MultipartFile imgUrl) {
        try {

            productRepository.findByProductName(dto.name()).ifPresent(product -> {
                throw new ProductAlredyExists();
            });

            var pic = cloudinary.uploader().upload(imgUrl.getBytes(), ObjectUtils.asMap("folder", "/productImg/"));


            var categoryId = new CategoryEntity();
            categoryId.setCategoryId(UUID.fromString(dto.categoryId()));

            var category = categoryRepository.findById(UUID.fromString(dto.categoryId()))
                    .orElseThrow(() -> new RuntimeException("Category not found for ID: " + dto.categoryId()));

            var newProduct = new ProductEntity();
            newProduct.setProductName(dto.name());
            newProduct.setDescription(dto.description());
            newProduct.setPrice(dto.price());
            newProduct.setImgUrl(pic.get("url").toString());
            newProduct.setCategory(category);

            return productRepository.save(newProduct);

        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Failed to upload the file.");
        }


    }
    public ProductResponseDTO getProductDetails(ProductResponseDTO dto, String productId) {
        return this.productRepository.findById(UUID.fromString(productId)).map(product -> {
            return new ProductResponseDTO(dto.name(), dto.description(), dto.price());
        }).orElseThrow(() -> new ProductDoesNotExists());
    }
}
