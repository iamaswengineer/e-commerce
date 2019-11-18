package tr.com.trendyol.can.ecommerce.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){super(message);}
}
