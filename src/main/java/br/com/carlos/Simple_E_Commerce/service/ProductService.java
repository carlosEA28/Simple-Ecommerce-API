package br.com.carlos.Simple_E_Commerce.service;

import br.com.carlos.Simple_E_Commerce.dto.ProductDto;
import br.com.carlos.Simple_E_Commerce.entity.CategoryEntity;
import br.com.carlos.Simple_E_Commerce.entity.ProductEntity;
import br.com.carlos.Simple_E_Commerce.exception.ProductAlredyExists;
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
    private Cloudinary cloudinary;

    public ProductEntity createProduct(ProductDto dto, MultipartFile imgUrl) {
        try {

            productRepository.findByProductName(dto.name()).ifPresent(product -> {
                throw new ProductAlredyExists();
            });

            File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + imgUrl.getOriginalFilename());
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(imgUrl.getBytes());
            fos.close();

            var pic = cloudinary.uploader().upload(convFile, ObjectUtils.asMap("folder", "/productImg/"));

            var categoryId = new CategoryEntity();
            categoryId.setCategoryId(UUID.fromString(dto.categoryId()));

            var newProduct = new ProductEntity();

            newProduct.setProductName(dto.name());
            newProduct.setDescription(dto.description());
            newProduct.setPrice(dto.price());
            newProduct.setImgUrl(pic.get("url").toString());
            newProduct.setCategory(categoryId);

            return productRepository.save(newProduct);

        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Failed to upload the file.");
        }
    }
}
