package tensor;

public class Tensors {
    public static Scalar scalarAdd(Scalar lhs, Scalar rhs) {
        return Scalar.add(lhs, rhs);
    }

    public static Scalar scalarMul(Scalar lhs, Scalar rhs) {
        return Scalar.mul(lhs, rhs);
    }

    public static Vector vectorAdd(Vector lhs, Vector rhs) throws CalculateNotSupportedException {
        return Vector.add(lhs, rhs);
    }

    public static Vector vectorMul(Vector lhs, Scalar rhs) throws CalculateNotSupportedException {
        return Vector.mul(lhs, rhs);
    }

    public static Matrix matrixAdd(Matrix lhs, Matrix rhs) throws CalculateNotSupportedException {
        return Matrix.add(lhs, rhs);
    }

    public static Matrix matrixMul(Matrix lhs, Matrix rhs) throws CalculateNotSupportedException {
        return Matrix.mul(lhs, rhs);
    }

    public static Matrix matrixConcatAsCol(Matrix lhs, Matrix rhs) throws CalculateNotSupportedException {
        return Matrix.concatAsCol(lhs, rhs);
    }

    public static Matrix matrixConcatAsRow(Matrix lhs, Matrix rhs) throws CalculateNotSupportedException {
        return Matrix.concatAsRow(lhs, rhs);
    }

}
