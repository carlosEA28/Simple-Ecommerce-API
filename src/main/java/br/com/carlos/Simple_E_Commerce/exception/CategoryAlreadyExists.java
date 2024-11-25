package br.com.carlos.Simple_E_Commerce.exception;

public class CategoryAlreadyExists extends RuntimeException {
    public CategoryAlreadyExists() {
        super("The category already exists");
    }
}
