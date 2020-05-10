package Exceptions;

public class LoginFail extends Exception {

        public LoginFail() {
            super(String.format("Incorrect username or password"));
        }


}
