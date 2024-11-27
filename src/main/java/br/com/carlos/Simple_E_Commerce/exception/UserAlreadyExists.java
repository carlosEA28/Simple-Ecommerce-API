package br.com.carlos.Simple_E_Commerce.exception;

public class UserAlreadyExists extends RuntimeException {
    public UserAlreadyExists() {
        super("User already exists");
    }
}
