package br.com.carlos.Simple_E_Commerce.repository;

import br.com.carlos.Simple_E_Commerce.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdminInterace extends JpaRepository<AdminEntity, UUID> {
}
