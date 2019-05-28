package tensor;

import tensor.*;

import java.io.FileNotFoundException;

public class Factory {
    public static Scalar createScalarFromInitialValue(Double initValue) {
        return null;
    }

    public static Scalar createScalarFromRandomValue(Double minValue, Double maxValue) {
        return null;
    }

    public static Vector createVectorFromInitialValue(int size, Scalar initValue) throws SizeLessThanZeroException {
        return null;
    }

    public static Vector createVectorFromRandomValue(int size, Scalar minValue, Scalar maxValue) throws SizeLessThanZeroException {
        return null;
    }

    public static Vector createVectorFromArray(Scalar[] array) throws SizeLessThanZeroException {
        return null;
    }

    public static Matrix createIdentityMatrix(int n) throws SizeLessThanZeroException {
        return null;
    }

    public static Matrix createMatrixFromInitialValue(int row, int col, Scalar initValue) throws SizeLessThanZeroException {
        return null;
    }

    public static Matrix createMatrixFromRandomValue(int row, int col, Scalar minValue, Scalar maxValue) throws SizeLessThanZeroException {
        return null;
    }

    public static Matrix createMatrixFromCSVFile(String csvFilePath) throws SizeLessThanZeroException, WrongCSVFormatException, FileNotFoundException  {
        return null;
    }

    public static Matrix createMatrixFromArray(Scalar[][] array) {
        return null;
    }
}
