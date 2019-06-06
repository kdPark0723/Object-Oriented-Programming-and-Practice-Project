package tensor;

import java.io.FileNotFoundException;

public class Factory {
    public static Scalar createScalarFromInitialValue(Double initValue) {
        return new ScalarImpl(initValue);
    }

    public static Scalar createScalarFromRandomValue(Double minValue, Double maxValue) {
        return new ScalarImpl(minValue, maxValue);
    }

    public static Vector createVectorFromInitialValue(int size, Scalar initValue) throws SizeLessThanZeroException {
        return new VectorImpl(size, initValue);
    }

    public static Vector createVectorFromRandomValue(int size, Scalar minValue, Scalar maxValue) throws SizeLessThanZeroException {
        return new VectorImpl(size, minValue, maxValue);
    }

    public static Vector createVectorFromArray(Scalar[] array) {
        return new VectorImpl(array);
    }

    public static Matrix createIdentityMatrix(int n) throws SizeLessThanZeroException {
        return new MatrixImpl(n);
    }

    public static Matrix createMatrixFromInitialValue(int row, int col, Scalar initValue) throws SizeLessThanZeroException {
        return new MatrixImpl(row, col, initValue);
    }

    public static Matrix createMatrixFromRandomValue(int row, int col, Scalar minValue, Scalar maxValue) throws SizeLessThanZeroException {
        return new MatrixImpl(row, col, minValue, maxValue);
    }

    public static Matrix createMatrixFromCSVFile(String csvFilePath) throws WrongCSVFormatException, FileNotFoundException {
        return new MatrixImpl(csvFilePath);
    }

    public static Matrix createMatrixFromArray(Scalar[][] array) {
        return new MatrixImpl(array);
    }
}
