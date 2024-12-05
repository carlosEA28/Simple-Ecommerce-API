package br.com.carlos.Simple_E_Commerce.controller;

import br.com.carlos.Simple_E_Commerce.dto.*;
import br.com.carlos.Simple_E_Commerce.entity.CustomerEntity;
import br.com.carlos.Simple_E_Commerce.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @PostMapping("/register")
    public ResponseEntity<CustomerEntity> register(@RequestBody @Valid CustomerDto customerDto) {
        var customer = customerService.createCustomer(customerDto);

        return ResponseEntity.ok().body(customer);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginDto dto) {
        var token = customerService.login(dto);

        return ResponseEntity.ok().body(token);
    }

    @PostMapping("/redeem-password")
    public ResponseEntity<Map<String, String>> redeemPassword(@RequestBody @Valid CustomerReedemPasswordDto customerReedemPasswordDto) {
        customerService.redeemPassword(customerReedemPasswordDto.email());

        return ResponseEntity.ok().body(Map.of("message", "Send the redeem password link to your email"));
    }


    @PostMapping("/reset-password")
    public ResponseEntity<Map<String, String>> resetPassword(@RequestBody @Valid CustomerResetPasswordDto dto) {
        customerService.resetPassword(dto);

        return ResponseEntity.ok().body(Map.of("message", "Credentials updated"));
    }
}
