package br.com.carlos.Simple_E_Commerce.service;

import br.com.carlos.Simple_E_Commerce.entity.CartEntity;
import br.com.carlos.Simple_E_Commerce.entity.ProductEntity;
import br.com.carlos.Simple_E_Commerce.exception.ProductDoesNotExists;
import br.com.carlos.Simple_E_Commerce.exception.UserNotFound;
import br.com.carlos.Simple_E_Commerce.repository.CartRepository;
import br.com.carlos.Simple_E_Commerce.repository.CustomerRepository;
import br.com.carlos.Simple_E_Commerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class CartService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public void addItemCarrinho(String customerId, String productId) {
        var cart = new CartEntity();

        var customerExists = customerRepository.findByCustomerId(UUID.fromString(customerId)).orElseThrow(UserNotFound::new);
        var productExists = productRepository.findById(UUID.fromString(productId)).orElseThrow(ProductDoesNotExists::new);

        cart.setCustomer(customerExists);
        cart.setProduct(new ArrayList<>());
        cart.getProduct().add(productExists);

        cartRepository.save(cart);
    }

    public List<CartEntity> getCartItems(String customerId) {
        customerRepository.findByCustomerId(UUID.fromString(customerId))
                .orElseThrow(UserNotFound::new);

        return cartRepository.findByCustomerCustomerId(UUID.fromString(customerId));
    }

    public double calculateCartTotal(String customerId) {
        var customer = customerRepository.findByCustomerId(UUID.fromString(customerId))
                .orElseThrow(() -> new UserNotFound());


        var carts = cartRepository.findByCustomerCustomerId(customer.getCustomerId());
        double totalPrice = 0.0;

        for (CartEntity cartEntity : carts) {
            var products = cartEntity.getProduct();

            for (ProductEntity product : products) {
                totalPrice += product.getPrice();
            }
        }

        return totalPrice;
    }
}


