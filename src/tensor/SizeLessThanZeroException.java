package tensor;

public class SizeLessThanZeroException extends RuntimeException {
    public SizeLessThanZeroException() {

    }

    public SizeLessThanZeroException(String message) {
        super(message);
    }
}
