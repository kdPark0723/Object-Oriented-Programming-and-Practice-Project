package tensor;

public class CalculateNotSupportedException extends RuntimeException {
    public CalculateNotSupportedException() {

    }

    public CalculateNotSupportedException(String message) {
        super(message);
    }
}
