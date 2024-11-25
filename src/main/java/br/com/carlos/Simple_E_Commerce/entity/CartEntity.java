package br.com.carlos.Simple_E_Commerce.entity;

import jakarta.persistence.*;
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
@Table(name = "tb_cart")
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID cartId;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    //fazer aqui ou fazer um endpoint
    public Double GetTotal() {
        return null;
    }
}
