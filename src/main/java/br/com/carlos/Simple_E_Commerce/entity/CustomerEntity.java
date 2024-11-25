package br.com.carlos.Simple_E_Commerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tb_customer")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID customerId;

    @NotBlank(message = "The field [name] cannot be empty")
    private String name;

    @NotBlank(message = "The field [address] cannot be empty")
    private String address;

    @NotBlank(message = "The field [email] cannot be empty")
    @Email(message = "The field [email] must have a valid type")
    private String email;

    @NotBlank(message = "The field [name] cannot be empty")
    private String password;

    @NotBlank(message = "The field [name] cannot be empty")
    private LocalDate createdAt;

    @OneToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;


}
