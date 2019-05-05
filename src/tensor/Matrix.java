package tensor;

public interface Matrix extends Cloneable {
    class Size {
        public int row, col;

        public Size(int col, int row) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object obj) {
            return ((Size)obj).row == this.row && ((Size)obj).col == this.col;
        }
    }

    class Range {
        public int begin, end;

        public Range(int begin, int end) {
            this.begin = begin;
            this.end = end;
        }
    }

    static Matrix createIdentityMatrix(int size) throws SizeLessThanZeroException {
        return null;
    }

    static Matrix createMatrixFromInitialValue(int colSize, int rowSize, Scalar initialValue) throws SizeLessThanZeroException {
        return null;
    }

    static Matrix createMatrixFromRandomValue(int colSize, int rowSize, Scalar minValue, Scalar maxValue) throws SizeLessThanZeroException {
        return null;
    }

    static Matrix createMatrixFromCSVFile(String csvFilePath) throws SizeLessThanZeroException, WrongCSVFormatException {
        return null;
    }

    static Matrix createMatrixFromArray(Scalar[][] array) {
        return null;
    }

    Scalar get(int col, int row) throws IndexOutOfBoundException;

    void set(int col, int row, Scalar value) throws IndexOutOfBoundException;

    void clear(Scalar value);

    Size size();

    static Matrix add(Matrix lhs, Matrix rhs) throws CalculateNotSupportedException {
        try {
            Matrix result = (Matrix)lhs.clone();
            result.add(rhs);
            return result;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    void add(Matrix rhs) throws CalculateNotSupportedException;

    static Matrix mul(Matrix lhs, Matrix rhs) throws CalculateNotSupportedException {
        try {
            Matrix result = (Matrix)lhs.clone();
            result.mul(rhs);
            return result;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    void mul(Matrix rhs) throws CalculateNotSupportedException;

    static Matrix addRowVector(Matrix lhs, Matrix rhs) throws CalculateNotSupportedException {
        try {
            Matrix result = (Matrix)lhs.clone();
            result.addRowVector(rhs);
            return result;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    void addRowVector(Matrix rhs) throws CalculateNotSupportedException;

    static Matrix addColVector(Matrix lhs, Matrix rhs) throws CalculateNotSupportedException {
        try {
            Matrix result = (Matrix)lhs.clone();
            result.addColVector(rhs);
            return result;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    void addColVector(Matrix rhs) throws CalculateNotSupportedException;

    Vector getRow(int rowIndex) throws IndexOutOfBoundException;

    Vector getCol(int colIndex) throws IndexOutOfBoundException;

    void swapRow(int i, int j) throws IndexOutOfBoundException;

    void swapCol(int i, int j) throws IndexOutOfBoundException;

    Matrix getSub(Range col, Range row) throws IndexOutOfBoundException;

    Matrix getMinor(int removedCol, int removedRow) throws IndexOutOfBoundException;

    Matrix getTranspose();

    Scalar getTrace() throws CalculateNotSupportedException;

    Matrix getRREF();

    boolean isRREF();

    boolean isSquare();

    boolean isUpperTriangular();

    boolean isLowerTriangular();

    boolean isIdentity();

    boolean isZero();

    Scalar getDeterminant();

    Matrix getInverse();

    Object clone() throws CloneNotSupportedException;
}