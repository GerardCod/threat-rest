package dev.gerardo.threatrest.apirest.exceptions;

public class IpAlreadyBannedException extends RuntimeException {

    private static final long serialVersionUID = 123456789L;

    public IpAlreadyBannedException(String message) {
        super(message);
    }

}
