package tensor;

public interface Vector extends Cloneable {
    Scalar get(int index) throws IndexOutOfBoundsException;

    void set(int index, Scalar value) throws IndexOutOfBoundsException;

    void set(Vector weekCoped);

    void swap(Vector swapped);

    void clear(Scalar value);

    int size();

    static Vector add(Vector lhs, Vector rhs) throws CalculateNotSupportedException {
        return null;
    }

    void add(Vector rhs) throws CalculateNotSupportedException;

    static Vector mul(Vector lhs, Scalar rhs) {
        return null;
    }

    void mul(Scalar rhs);

    Matrix toMatrix1xN();

    Matrix toMatrixNx1();

    Object clone() throws CloneNotSupportedException;
}
