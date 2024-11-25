package br.com.carlos.Simple_E_Commerce.exception;

public class ProductDoesNotExists extends RuntimeException {
    public ProductDoesNotExists() {
        super("The product does not exist");
    }
}
