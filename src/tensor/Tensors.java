package tensor;

public class Tensors {
    public static Scalar scalarAdd(Scalar lhs, Scalar rhs) {
        return Scalar.add(lhs, rhs);
    }

    public static Scalar scalarMul(Scalar lhs, Scalar rhs) {
        return Scalar.mul(lhs, rhs);
    }

    public static Vector vectorAdd(Vector lhs, Vector rhs) {
        return Vector.add(lhs, rhs);
    }

    public static Vector vectorMul(Vector lhs, Scalar rhs) {
        return Vector.mul(lhs, rhs);
    }

    public static Matrix MatrixAddRowVector(Matrix lhs, Matrix rhs) throws CalculateNotSupportedException {
        return Matrix.addRowVector(lhs, rhs);
    }

    public static Matrix MatrixAddColVector(Matrix lhs, Matrix rhs) throws CalculateNotSupportedException {
        return Matrix.addColVector(lhs, rhs);
    }
}
