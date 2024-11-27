package br.com.carlos.Simple_E_Commerce.controller;

import br.com.carlos.Simple_E_Commerce.dto.CustomerDto;
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


//    @PostMapping("/login")
//    public ResponseEntity<> login(@RequestBody @Valid UserDto userDto) {
//        var token = customerService.login(userDto.email(), userDto.password());
//        return ResponseEntity.ok().body(Map.of("token", token));
//    }

    @PostMapping("/register")
    public ResponseEntity<CustomerEntity> register(@RequestBody @Valid CustomerDto customerDto) {
        var customer = customerService.createCustomer(customerDto);

        return ResponseEntity.ok().body(customer);
    }

//    @PostMapping("/redeem-password")
//    public ResponseEntity<> redeemPassword(@RequestBody @Valid UserRedeemPasswordDto userDto) {
//        customerService.redeemPassword(userDto.email());
//
//        return ResponseEntity.ok().body(Map.of("message", "Send the redeem password link to your email"));
//    }
//
//    @PostMapping("/reset-password")
//    public ResponseEntity<> resetPassword(@RequestBody @Valid UserResetPasswordDto userDto) {
//        customerService.resetPassword(userDto.token(), userDto.password());
//
//        return ResponseEntity.ok().body(Map.of("message", "Credentials updated"));
//    }
}
