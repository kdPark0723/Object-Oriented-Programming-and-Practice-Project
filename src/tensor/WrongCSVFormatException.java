package tensor;

public class WrongCSVFormatException extends RuntimeException {
    public WrongCSVFormatException() {

    }

    public WrongCSVFormatException(String message) {
        super(message);
    }
}