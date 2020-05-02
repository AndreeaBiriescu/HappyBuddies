package Exceptions;

public class UsernameAlreadyExistsException extends Exception {
    public UsernameAlreadyExistsException() {
        super(String.format("Username already exists!"));
    }
}
