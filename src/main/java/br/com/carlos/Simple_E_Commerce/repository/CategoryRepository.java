package br.com.carlos.Simple_E_Commerce.repository;

import br.com.carlos.Simple_E_Commerce.entity.CategoryEntity;
import br.com.carlos.Simple_E_Commerce.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {
    Optional<CategoryEntity> findByCategoryName(String name);

}
