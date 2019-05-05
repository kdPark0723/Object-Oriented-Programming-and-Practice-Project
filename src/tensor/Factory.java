package tensor;

import tensor.*;

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

    public static Vector createVectorFromArray(Scalar[] array) throws SizeLessThanZeroException {
        return new VectorImpl(array);
    }

    public static Matrix createIdentityMatrix(int n) throws SizeLessThanZeroException {
        return new MatrixImpl(n);
    }

    public static Matrix createMatrixFromInitialValue(int col, int row, Scalar initValue) throws SizeLessThanZeroException {
        return new MatrixImpl(col, row, initValue);
    }

    public static Matrix createMatrixFromRandomValue(int col, int row, Scalar minValue, Scalar maxValue) throws SizeLessThanZeroException {
        return new MatrixImpl(col, row, minValue, maxValue);
    }

    public static Matrix createMatrixFromCSVFile(String csvFilePath) throws SizeLessThanZeroException, WrongCSVFormatException, FileNotFoundException  {
        return new MatrixImpl(csvFilePath);
    }

    public static Matrix createMatrixFromArray(Scalar[][] array) {
        return new MatrixImpl(array);
    }
}
