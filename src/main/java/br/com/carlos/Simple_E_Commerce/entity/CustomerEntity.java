package br.com.carlos.Simple_E_Commerce.entity;

import br.com.carlos.Simple_E_Commerce.Enums.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @Column(name = "customer_id", nullable = false, updatable = false) // Nome da coluna no banco
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

    private String resetToken;
    private Instant resetTokenExpiration;

    @Enumerated(EnumType.STRING)
    private Role role;

    @NotNull(message = "A data de criação não pode ser nula.")
    private LocalDateTime createdAt;

    public CustomerEntity withResetToken(String resetToken, Instant resetTokenAdditionalTime) {
        this.resetToken = resetToken;
        this.resetTokenExpiration = resetTokenAdditionalTime;
        return this;
    }

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }

    @OneToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private CartEntity cart;
}
