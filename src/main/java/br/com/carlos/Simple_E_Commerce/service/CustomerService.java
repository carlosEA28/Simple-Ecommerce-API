package br.com.carlos.Simple_E_Commerce.service;

import br.com.carlos.Simple_E_Commerce.Domains.EmailService;
import br.com.carlos.Simple_E_Commerce.Enums.Role;
import br.com.carlos.Simple_E_Commerce.dto.CustomerDto;
import br.com.carlos.Simple_E_Commerce.entity.CustomerEntity;
import br.com.carlos.Simple_E_Commerce.exception.UserAlreadyExists;
import br.com.carlos.Simple_E_Commerce.exception.UserNotFound;
import br.com.carlos.Simple_E_Commerce.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Value("${token.expiration.seconds:300}")
    private long tokenExpirationSeconds;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

//    @Autowired
//    private JwtActions jwtActions;

    @Autowired
    private EmailService emailService;


    private void sendPasswordResetEmail(String email, String token) {
        // Assunto do e-mail
        String subject = "Password Reset Request";

        // URL de redefinição de senha
        String resetUrl = String.format("https://Ecommerce.com/reset-password?token=%s", token);

        // Corpo da mensagem do e-mail
        String body = String.format(
                """
                        Olá,
                        
                        Recebemos uma solicitação para redefinir sua senha. \
                        Clique no link abaixo para redefinir sua senha:
                        
                        %s
                        
                        Se você não solicitou a redefinição de senha, ignore este e-mail.
                        
                        Atenciosamente,
                        Equipe do Ecommerce""",
                resetUrl
        );

        // Serviço de envio de e-mail (assumindo que `emailService` esteja configurado)
        emailService.sendEmail(email, subject, body);
    }

    public CustomerEntity createCustomer(CustomerDto customerDto) {
        customerRepository.findByEmail(customerDto.email()).ifPresent(user -> {
            throw new UserAlreadyExists();
        });

        var customer = new CustomerEntity();
        customer.setName(customerDto.name());
        customer.setAddress(customerDto.address());
        customer.setEmail(customerDto.email());
        customer.setPassword(bCryptPasswordEncoder.encode(customerDto.password()));
        customer.setRole(Role.CLIENT);

        return customerRepository.save(customer);
    }

}
