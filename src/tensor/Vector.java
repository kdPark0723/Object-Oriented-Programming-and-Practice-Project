package tensor;

public interface Vector extends Cloneable {

    void add(Vector rhs) throws CalculateNotSupportedException;
    void mul(Scalar rhs);

    Scalar get(int index) throws IndexOutOfBoundsException;

    void set(int index, Scalar value) throws IndexOutOfBoundsException;

    void swap(Vector swapped);

    void clear(Scalar value);

    Object clone() throws CloneNotSupportedException;

    Matrix toMatrix1xN();
    Matrix toMatrixNx1();

    int size();
}
