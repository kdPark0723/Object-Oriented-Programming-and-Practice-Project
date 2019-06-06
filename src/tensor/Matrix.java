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

    void add(Matrix rhs) throws CalculateNotSupportedException;

    void mulByLeft(Matrix rhs) throws CalculateNotSupportedException;
    void mulByRight(Matrix rhs) throws CalculateNotSupportedException;

    Matrix concatAsRow(Matrix rhs) throws CalculateNotSupportedException;
    Matrix concatAsCol(Matrix rhs) throws CalculateNotSupportedException;

    void swapRow(int i, int j) throws IndexOutOfBoundsException;
    void swapCol(int i, int j) throws IndexOutOfBoundsException;

    void mulRow(int index, Scalar multiply) throws IndexOutOfBoundsException;
    void mulCol(int index, Scalar multiply) throws IndexOutOfBoundsException;

    void mulRowAndAddOther(int destination, int source, Scalar multiply) throws IndexOutOfBoundsException;
    void mulColAndAddOther(int destination, int source, Scalar multiply) throws IndexOutOfBoundsException;

    Scalar get(int row, int col) throws IndexOutOfBoundsException;

    Vector getRow(int rowIndex) throws IndexOutOfBoundsException;
    Vector getCol(int colIndex) throws IndexOutOfBoundsException;

    Matrix getSub(Range row, Range col) throws IndexOutOfBoundsException;
    Matrix getMinor(int removedRow, int removedCol) throws IndexOutOfBoundsException;

    Matrix getTranspose();

    Scalar getTrace() throws CalculateNotSupportedException;

    Matrix getRREF();

    Scalar getDeterminant() throws CalculateNotSupportedException;

    Matrix getInverse() throws CalculateNotSupportedException;

    void set(int row, int col, Scalar value) throws IndexOutOfBoundsException;

    boolean isRREF();

    boolean isSquare();

    boolean isUpperTriangular() throws CalculateNotSupportedException;
    boolean isLowerTriangular() throws CalculateNotSupportedException;

    boolean isIdentity() throws CalculateNotSupportedException;

    boolean isZero();

    Object clone();

    void clear(Scalar value);

    Size size();
}