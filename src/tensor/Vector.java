package tensor;

public interface Vector extends Cloneable {
    void add(Vector rhs) throws CalculateNotSupportedException;
    void mul(Scalar rhs);

    Scalar get(int index) throws IndexOutOfBoundsException;

    Object clone();

    int size();

    void set(int index, Scalar value) throws IndexOutOfBoundsException;

    Matrix toMatrix1xN();
    Matrix toMatrixNx1();
}
