package src.exceptions;

public class NoSuchPathException extends RuntimeException{
    public NoSuchPathException(String message){
        super(message);
    }
}
