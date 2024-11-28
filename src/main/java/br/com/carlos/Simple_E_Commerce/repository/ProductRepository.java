package br.com.carlos.Simple_E_Commerce.repository;

import br.com.carlos.Simple_E_Commerce.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
    Optional<ProductEntity> findByProductName(String name);

    List<ProductEntity> findByCategory_CategoryId(UUID categoryId);


}
