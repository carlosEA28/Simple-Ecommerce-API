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

import java.io.IOException;
import java.util.List;
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

    public ProductEntity getProductDetailsEntity(String productId) {

        return productRepository.findById(UUID.fromString(productId))
                .orElseThrow(ProductDoesNotExists::new);
    }


    public ProductResponseDTO getProductDetail(String productId) {
        var product = productRepository.findById(UUID.fromString(productId))
                .orElseThrow(ProductDoesNotExists::new);

        String categoryName = product.getCategory().getCategoryName();

        return new ProductResponseDTO(product.getProductName(), product.getDescription(), product.getPrice(), categoryName);
    }

    public List<ProductResponseDTO> getAllProductsByCategory(UUID categoryId) {
        var products = productRepository.findByCategory_CategoryId(categoryId);

        if (products.isEmpty()) {
            throw new ProductDoesNotExists();
        }

        return products.stream()
                .map(product -> new ProductResponseDTO(
                        product.getProductName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getCategory().getCategoryName()
                ))
                .toList();
    }

    public void deleteProduct(String productId) {
        productRepository.deleteById(UUID.fromString(productId));

    }

}

