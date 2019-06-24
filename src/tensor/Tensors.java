package tensor;

public class Tensors {
    public static Scalar scalarAdd(Scalar lhs, Scalar rhs) {
        return ScalarImpl.add(lhs, rhs);
    }

    public static Scalar scalarMul(Scalar lhs, Scalar rhs) {
        return ScalarImpl.mul(lhs, rhs);
    }

    public static Vector vectorAdd(Vector lhs, Vector rhs) throws CalculateNotSupportedException {
        return VectorImpl.add(lhs, rhs);
    }

    public static Vector vectorMul(Vector lhs, Scalar rhs) throws CalculateNotSupportedException {
        return VectorImpl.mul(lhs, rhs);
    }

    public static Matrix matrixAdd(Matrix lhs, Matrix rhs) throws CalculateNotSupportedException {
        return MatrixImpl.add(lhs, rhs);
    }

    public static Matrix matrixMul(Matrix lhs, Matrix rhs) throws CalculateNotSupportedException {
        return MatrixImpl.mul(lhs, rhs);
    }

    public static Matrix matrixConcatAsCol(Matrix lhs, Matrix rhs) throws CalculateNotSupportedException {
        return MatrixImpl.concatAsCol(lhs, rhs);
    }

    public static Matrix matrixConcatAsRow(Matrix lhs, Matrix rhs) throws CalculateNotSupportedException {
        return MatrixImpl.concatAsRow(lhs, rhs);
    }

}
