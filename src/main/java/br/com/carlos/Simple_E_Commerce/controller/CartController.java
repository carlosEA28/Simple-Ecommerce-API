package br.com.carlos.Simple_E_Commerce.controller;

import br.com.carlos.Simple_E_Commerce.dto.ProductDto;
import br.com.carlos.Simple_E_Commerce.entity.CartEntity;
import br.com.carlos.Simple_E_Commerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addItemToCart(
            @RequestHeader("customerId") String customerId,
            @RequestHeader("productId") String productId,
            @RequestHeader("dto") ProductDto dto) {

        cartService.addItemCarrinho(customerId, productId);

        Map<String, String> response = Map.of(
                "status", "Ok",
                "message", "Product added successfully"
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<CartEntity>> getCartProducts(@RequestHeader String customerId) {
        var products = cartService.getCartItems(customerId);

        return ResponseEntity.ok().body(products);
    }

}
