package br.com.carlos.Simple_E_Commerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tb_product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID productId;

    @NotBlank(message = "The field [ProductName] cannot be empty")
    private String productName;

    @NotBlank(message = "The field [description] cannot be empty")
    private String description;

    @NotNull(message = "The field [price] cannot be null")
    private Double price;

    @NotNull(message = "The field [imgUrl] cannot be null")
    private String imgUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;


}
