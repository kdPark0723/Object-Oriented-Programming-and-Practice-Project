package tensor;

public interface Vector extends Cloneable {
    Scalar get(int index) throws IndexOutOfBoundsException;

    void set(int index, Scalar value) throws IndexOutOfBoundsException;

    void set(Vector weekCoped);

    void swap(Vector swapped);

    void clear(Scalar value);

    int size();

    static Vector add(Vector lhs, Vector rhs) throws CalculateNotSupportedException {
        try {
            Vector result = (Vector)lhs.clone();
            result.add(rhs);
            return result;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    void add(Vector rhs) throws CalculateNotSupportedException;

    static Vector mul(Vector lhs, Scalar rhs) {
        try {
            Vector result = (Vector)lhs.clone();
            result.mul(rhs);
            return result;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    void mul(Scalar rhs);

    Object clone() throws CloneNotSupportedException;

    Matrix toMatrix1xN();

    Matrix toMatrixNx1();
}
