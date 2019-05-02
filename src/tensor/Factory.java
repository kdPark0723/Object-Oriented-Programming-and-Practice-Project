package tensor;

import tensor.*;

import java.io.FileNotFoundException;

public class Factory {
    public static Scalar createScalar(Double initValue) {
        return new ScalarImpl(initValue);
    }

    public static Scalar createScalar(Double minValue, Double maxValue) {
        return new ScalarImpl(minValue, maxValue);
    }

    public static Vector createVector(int size, Scalar initValue) {
        return new VectorImpl(size, initValue);
    }

    public static Vector createVector(int size, Scalar minValue, Scalar maxValue) {
        return new VectorImpl(size, minValue, maxValue);
    }

    public static Vector createVector(Scalar[] array) {
        return new VectorImpl(array);
    }

    public static Matrix createMatrix(int n) {
        return new MatrixImpl(n);
    }

    public static Matrix createMatrix(int col, int row, Scalar initValue) {
        return new MatrixImpl(col, row, initValue);
    }

    public static Matrix createMatrix(int col, int row, Scalar minValue, Scalar maxValue) {
        return new MatrixImpl(col, row, minValue, maxValue);
    }

    public static Matrix createMatrix(String csvFilePath) throws FileNotFoundException {
        return new MatrixImpl(csvFilePath);
    }

    public static Matrix createMatrix(Scalar[][] array) {
        return new MatrixImpl(array);
    }
}
