package br.com.carlos.Simple_E_Commerce.entity;

import br.com.carlos.Simple_E_Commerce.Enums.Role;
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
@Table(name = "tb_admin")
public class AdminEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    private UUID adminId;

    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
