package br.com.carlos.Simple_E_Commerce.exception;

public class ProductAlredyExists extends RuntimeException {
    public ProductAlredyExists() {
        super("Product Already exists");
    }
}
