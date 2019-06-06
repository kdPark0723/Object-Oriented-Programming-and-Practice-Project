package tensor;

public interface Vector extends Cloneable {

    void add(Vector rhs) throws CalculateNotSupportedException;
    void mul(Scalar rhs);

    Scalar get(int index) throws IndexOutOfBoundsException;

    void set(int index, Scalar value) throws IndexOutOfBoundsException;

    Object clone();

    Matrix toMatrix1xN();
    Matrix toMatrixNx1();

    int size();
}
