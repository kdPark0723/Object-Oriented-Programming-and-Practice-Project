package tensor;

public interface Vector extends Cloneable {
    static Vector createVectorFromInitialValue(int size, Scalar initValue) throws SizeLessThanZeroException {
        return null;
    }

    static Vector createVectorFromRandomValue(int size, Scalar minValue, Scalar maxValue) throws SizeLessThanZeroException {
        return null;
    }

    static Vector createVectorFromArray(Scalar[] array) throws SizeLessThanZeroException {
        return null;
    }

    Scalar get(int index) throws IndexOutOfBoundException;

    void set(int index, Scalar value) throws IndexOutOfBoundException;

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
