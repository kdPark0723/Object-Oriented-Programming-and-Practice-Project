package tensor;

import java.io.FileNotFoundException;

public interface Matrix extends Cloneable {
    class Size {
        public int row, col;

        public Size(int row, int col) {
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

    Scalar get(int row, int col) throws IndexOutOfBoundsException;

    void set(int row, int col, Scalar value) throws IndexOutOfBoundsException;

    void clear(Scalar value);

    Size size();

    static Matrix add(Matrix lhs, Matrix rhs) throws CalculateNotSupportedException {
        return null;
    }

    void add(Matrix rhs) throws CalculateNotSupportedException;

    static Matrix mul(Matrix lhs, Matrix rhs) throws CalculateNotSupportedException {
        return null;
    }

    void mul(Matrix rhs) throws CalculateNotSupportedException;

    static Matrix concatAsRow(Matrix lhs, Matrix rhs) throws CalculateNotSupportedException {
        return null;
    }

    Matrix concatAsRow(Matrix rhs) throws CalculateNotSupportedException;

    static Matrix concatAsCol(Matrix lhs, Matrix rhs) throws CalculateNotSupportedException {
        return null;
    }

    Matrix concatAsCol(Matrix rhs) throws CalculateNotSupportedException;

    static Matrix addRowVector(Matrix lhs, Matrix rhs) throws CalculateNotSupportedException {
        return null;
    }

    void addRowVector(Matrix rhs) throws CalculateNotSupportedException;

    static Matrix addColVector(Matrix lhs, Matrix rhs) throws CalculateNotSupportedException {
        return null;
    }

    void addColVector(Matrix rhs) throws CalculateNotSupportedException;

    Vector getRow(int rowIndex) throws IndexOutOfBoundsException;

    Vector getCol(int colIndex) throws IndexOutOfBoundsException;

    void swapRow(int i, int j) throws IndexOutOfBoundsException;

    void swapCol(int i, int j) throws IndexOutOfBoundsException;

    void mulRow(int index, Scalar multiply) throws IndexOutOfBoundsException;

    void mulCol(int index, Scalar multiply) throws IndexOutOfBoundsException;

    void mulRowAndAddOther(int destination, int source, Scalar multiply) throws IndexOutOfBoundsException;

    void mulColAndAddOther(int destination, int source, Scalar multiply) throws IndexOutOfBoundsException;

    Matrix getSub(Range row, Range col) throws IndexOutOfBoundsException;

    Matrix getMinor(int removedRow, int removedCol) throws IndexOutOfBoundsException;

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