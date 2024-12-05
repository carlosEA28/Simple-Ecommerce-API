package br.com.carlos.Simple_E_Commerce.repository;

import br.com.carlos.Simple_E_Commerce.entity.CartEntity;
import br.com.carlos.Simple_E_Commerce.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, UUID> {
    List<CartEntity> findByCustomerCustomerId(UUID customerId);
}

