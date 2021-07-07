package az.javafx.exceptions;

public class GenderNotValidException extends  RuntimeException{
    public GenderNotValidException() {
    }

    public GenderNotValidException(String message) {
        super(message);
    }
}
