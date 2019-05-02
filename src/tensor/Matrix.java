package tensor;

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

    Scalar get(int row, int col);

    void set(int row, int col, Scalar value);

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

    Vector row(int rowIndex);

    Vector col(int colIndex);

    Matrix subMatrix(Range row, Range col);

    Matrix minor(int removedRow, int removedCol);

    Matrix transpose();

    Scalar trace();

    void swapRow(int i, int j);

    void swapCol(int i, int j);

    Matrix getRREF();

    boolean isRREF();

    boolean isSquare();

    boolean isUpperTriangular();

    boolean isLowerTriangular();

    boolean isIdentity();

    boolean isZero();

    Scalar determinant();

    Matrix inverse();

    Object clone() throws CloneNotSupportedException;
}