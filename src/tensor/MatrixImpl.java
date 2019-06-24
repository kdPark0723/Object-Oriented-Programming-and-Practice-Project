package tensor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

class MatrixImpl implements Matrix {
    private MatrixStorage data;

    MatrixImpl(int n) throws SizeLessThanZeroException {
        checkSize(n, n);

        data = new MatrixStorage(n, n, new ScalarImpl(0.0));

        for (int i = 0; i < n; ++i)
            data.set(i, i, new ScalarImpl(1.0));
    }

    MatrixImpl(int row, int col, Scalar initValue) throws SizeLessThanZeroException {
        checkSize(row, col);

        data = new MatrixStorage(row, col, initValue);
    }

    MatrixImpl(int row, int col, Scalar minValue, Scalar maxValue) throws SizeLessThanZeroException {
        checkSize(row, col);

        data = new MatrixStorage(row, col, new ScalarImpl(0.0));

        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++)
                data.set(i, j, new ScalarImpl(Util.getRandomValue(minValue.get(), maxValue.get())));

    }

    MatrixImpl(String csvFilePath) throws WrongCSVFormatException, FileNotFoundException {
        ArrayList<ArrayList<Scalar>> elements = readCSVFile(csvFilePath);

        data = new MatrixStorage(elements);
    }

    MatrixImpl(Scalar[][] array) {
        data = new MatrixStorage(array);
    }

    static Matrix add(Matrix lhs, Matrix rhs) throws CalculateNotSupportedException {
        Matrix result = lhs.clone();
        result.add(rhs);

        return result;
    }

    static Matrix mul(Matrix lhs, Matrix rhs) throws CalculateNotSupportedException {
        Matrix result = lhs.clone();
        result.mulByRight(rhs);

        return result;
    }

    static Matrix concatAsRow(Matrix lhs, Matrix rhs) throws CalculateNotSupportedException {
        Matrix result = lhs.clone();

        return result.concatAsRow(rhs);
    }

    static Matrix concatAsCol(Matrix lhs, Matrix rhs) throws CalculateNotSupportedException {
        Matrix result = lhs.clone();

        return result.concatAsCol(rhs);
    }

    private void checkSize(int row, int col) throws SizeLessThanZeroException {
        if (row < 0 || col < 0)
            throw new SizeLessThanZeroException("크기가 0보다 작습니다.");
    }

    private ArrayList<ArrayList<Scalar>> readCSVFile(String csvFilePath) throws WrongCSVFormatException, FileNotFoundException {
        ArrayList<ArrayList<Scalar>> elements = new ArrayList<>();

        File csvFile = new File(csvFilePath);

        readElementFormCSVFile(elements, csvFile);

        if (!isMatrixFormatValid(elements))
            throw new WrongCSVFormatException("CSV파일 형식이 잘못되었습니다");

        return elements;
    }

    private void readElementFormCSVFile(ArrayList<ArrayList<Scalar>> elements, File csvFile) throws FileNotFoundException {
        Scanner scanner = new Scanner(csvFile);

        for (int i = 0; scanner.hasNext(); i++) {
            elements.add(new ArrayList<>());

            String line = scanner.nextLine();

            StringTokenizer row = new StringTokenizer(line, ", ");
            while (row.hasMoreTokens())
                elements.get(i).add(Factory.createScalarFromInitialValue(Double.parseDouble(row.nextToken())));
        }

        scanner.close();
    }

    private boolean isMatrixFormatValid(ArrayList<ArrayList<Scalar>> elements) {
        int col = elements.get(0).size();
        for (ArrayList<Scalar> row : elements) {
            if (row.size() != col)
                return false;
        }

        return true;
    }

    @Override
    public void add(Matrix rhs) throws CalculateNotSupportedException {
        checkSizeEqual(rhs.size());

        Size size = this.size();
        for (int i = 0; i < size.row; i++)
            for (int j = 0; j < size.col; j++)
                this.get(i, j).add(rhs.get(i, j));

    }

    private void checkSizeEqual(Size size) throws CalculateNotSupportedException {
        if (!this.size().equals(size))
            throw new CalculateNotSupportedException("행렬의 크기가 달라 덧셈 연산을 할수 없습니다.");
    }

    @Override
    public void mulByLeft(Matrix rhs) throws CalculateNotSupportedException {
        Matrix tmp = rhs.clone();
        tmp.mulByRight(this);

        Size size = tmp.size();

        MatrixStorage newStorage = new MatrixStorage(size.row, size.col, Factory.createScalarFromInitialValue(0.0));
        for (int i = 0; i < size.row; i++) {
            for (int j = 0; j < size.col; j++) {
                newStorage.set(i, j, tmp.get(i, j));
            }
        }

        this.data = newStorage;
    }

    @Override
    public void mulByRight(Matrix rhs) throws CalculateNotSupportedException {
        Size thisSize = this.size();
        Size rhsSize = rhs.size();

        checkCanMul(rhsSize);

        MatrixStorage newStorage = new MatrixStorage(thisSize.row, rhsSize.col, Factory.createScalarFromInitialValue(0.0));

        for (int i = 0; i < thisSize.row; i++)
            for (int j = 0; j < rhsSize.col; j++)
                for (int k = 0; k < thisSize.col; k++)
                    newStorage.get(i, j).add(Tensors.scalarMul(this.get(i, k), rhs.get(k, j)));

        this.data = newStorage;
    }

    private void checkCanMul(Size size) throws CalculateNotSupportedException {
        if (this.size().col != size.row)
            throw new CalculateNotSupportedException("행렬이 곱셈 연산을 할수았는 크기가 아닙니다.");
    }

    @Override
    public Matrix concatAsRow(Matrix rhs) throws CalculateNotSupportedException {
        Size thisSize = this.size();
        Size rhsSize = rhs.size();

        checkCanConcatAsRow(rhsSize);

        MatrixStorage newStorage = new MatrixStorage(thisSize.row + rhsSize.row, thisSize.col, Factory.createScalarFromInitialValue(0.0));

        for (int i = 0; i < thisSize.row; i++) {
            for (int j = 0; j < thisSize.col; j++)
                newStorage.set(i, j, this.get(i, j));
        }
        for (int i = thisSize.row; i < thisSize.row + rhsSize.row; i++) {
            for (int j = 0; j < thisSize.col; j++)
                newStorage.set(i, j, rhs.get(i - thisSize.row, j));
        }

        this.data = newStorage;

        return this;
    }

    private void checkCanConcatAsRow(Size size) throws CalculateNotSupportedException {
        if (this.size().col != size.col)
            throw new CalculateNotSupportedException("행렬의 열수가 달라 합칠수 없습니다.");
    }

    @Override
    public Matrix concatAsCol(Matrix rhs) throws CalculateNotSupportedException {
        Size thisSize = this.size();
        Size rhsSize = rhs.size();

        checkCanConcatAsCol(rhsSize);

        MatrixStorage newStorage = new MatrixStorage(thisSize.row, thisSize.col + rhsSize.col, Factory.createScalarFromInitialValue(0.0));

        for (int i = 0; i < thisSize.row; i++) {
            for (int j = 0; j < thisSize.col; j++)
                newStorage.set(i, j, this.get(i, j));
            for (int j = thisSize.col; j < thisSize.col + rhsSize.col; j++)
                newStorage.set(i, j, rhs.get(i, j - thisSize.col));
        }

        this.data = newStorage;

        return this;
    }

    private void checkCanConcatAsCol(Size size) throws CalculateNotSupportedException {
        if (this.size().row != size.row)
            throw new CalculateNotSupportedException("행렬의 행수가 달라 합칠수 없습니다.");
    }

    @Override
    public void swapRow(int i, int j) {
        if (i == j)
            return;

        int size = this.size().col;

        for (int k = 0; k < size; k++)
            swap(this.get(i, k), this.get(j, k));
    }

    @Override
    public void swapCol(int i, int j) {
        if (i == j)
            return;

        int size = this.size().row;

        for (int k = 0; k < size; k++)
            swap(this.get(k, i), this.get(k, j));
    }

    private void swap(Scalar a, Scalar b) {
        ScalarImpl tmp = new ScalarImpl(a.get());

        a.set(b.get());
        b.set(tmp.get());
    }

    @Override
    public void mulRow(int index, Scalar multiply) throws IndexOutOfBoundsException {
        int size = size().col;

        for (int i = 0; i < size; ++i)
            get(index, i).mul(multiply);
    }

    @Override
    public void mulCol(int index, Scalar multiply) throws IndexOutOfBoundsException {
        int size = size().row;

        for (int i = 0; i < size; ++i)
            get(i, index).mul(multiply);
    }

    @Override
    public void mulRowAndAddOther(int destination, int source, Scalar multiply) throws IndexOutOfBoundsException {
        int size = size().col;

        for (int i = 0; i < size; ++i)
            get(destination, i).add(Tensors.scalarMul(get(source, i), multiply));
    }

    @Override
    public void mulColAndAddOther(int destination, int source, Scalar multiply) throws IndexOutOfBoundsException {
        int size = size().row;

        for (int i = 0; i < size; ++i)
            get(i, destination).add(Tensors.scalarMul(get(i, source), multiply));
    }

    @Override
    public Scalar get(int row, int col) {
        return data.get(row, col);
    }

    @Override
    public Vector getCol(int colIndex) {
        Vector colVector = new VectorImpl(this.size().row, new ScalarImpl(0.0));

        int colVectorSize = colVector.size();

        for (int i = 0; i < colVectorSize; i++) {
            colVector.set(i, this.get(i, colIndex));
        }

        return colVector;
    }

    @Override
    public Vector getRow(int rowIndex) {
        Vector rowVector = new VectorImpl(this.size().col, new ScalarImpl(0.0));

        int rowVectorSize = rowVector.size();

        for (int i = 0; i < rowVectorSize; i++) {
            rowVector.set(i, this.get(rowIndex, i));
        }

        return rowVector;
    }

    @Override
    public Matrix getSub(Range row, Range col) {
        int rowSize = row.end - row.begin;
        int colSize = col.end - col.begin;

        Scalar[][] data = new Scalar[rowSize][colSize];

        for (int i = row.begin; i < row.end; i++) {
            for (int j = col.begin; j < col.end; j++) {
                data[i - row.begin][j - col.begin] = this.get(i, j);
            }
        }

        return new MatrixImpl(data);
    }

    @Override
    public Matrix getMinor(int removedRow, int removedCol) {
        Size size = this.size();
        Scalar[][] data = new Scalar[size.row - 1][size.col - 1];

        for (int i = 0; i < removedRow; i++) {
            for (int j = 0; j < removedCol; j++) {
                data[i][j] = this.get(i, j);
            }
            for (int j = removedCol + 1; j < size.col; j++) {
                data[i][j - 1] = this.get(i, j);
            }
        }
        for (int i = removedRow + 1; i < size.row; i++) {
            for (int j = 0; j < removedCol; j++) {
                data[i - 1][j] = this.get(i, j);
            }
            for (int j = removedCol + 1; j < size.col; j++) {
                data[i - 1][j - 1] = this.get(i, j);
            }
        }

        return new MatrixImpl(data);
    }

    @Override
    public Matrix getTranspose() {
        Size size = this.size();

        Matrix transposed = new MatrixImpl(size.col, size.row, new ScalarImpl(0.0));

        for (int i = 0; i < size.col; i++)
            for (int j = 0; j < size.row; j++)
                transposed.set(i, j, this.get(j, i));

        return transposed;
    }

    @Override
    public Scalar getTrace() throws CalculateNotSupportedException {
        checkIsSquare();

        Scalar trace = new ScalarImpl(0.0);

        int size = this.size().row;
        for (int i = 0; i < size; i++)
            trace.add(this.get(i, i));

        return trace;
    }

    @Override
    public Matrix getRREF() {
        return getRREFByREF(getREF());
    }

    private Matrix getREF() {
        Matrix ref = clone();

        int row = ref.size().row;
        int col = ref.size().col;

        Scalar zero = Factory.createScalarFromInitialValue(0.0);

        for (int i = 0; i < row; ++i) {
            int j = col + 1;
            int hasMinColNotZeroRow = i;

            for (int k = i; k < row; ++k) {
                for (int l = 0; l < col; ++l) {
                    if (!ref.get(k, l).equals(zero) && l < j) {
                        j = l;
                        hasMinColNotZeroRow = k;
                    }
                }
            }

            if (j == col + 1)
                break;

            ref.swapRow(i, hasMinColNotZeroRow);

            for (int k = i + 1; k < row; ++k) {
                Scalar mul = Factory.createScalarFromInitialValue(-1.0);
                mul.mul(ref.get(k, j));
                mul.div(ref.get(i, j));

                ref.mulRowAndAddOther(k, i, mul);
            }

        }

        return ref;
    }

    private Matrix getRREFByREF(Matrix ref) {
        Matrix rref = ref;

        int row = rref.size().row;
        int col = rref.size().col;

        Scalar zero = Factory.createScalarFromInitialValue(0.0);

        for (int i = row - 1; i >= 0; --i) {
            int j = 0;
            for (; j < col; ++j) {
                if (!rref.get(i, j).equals(zero))
                    break;
            }

            if (j == col)
                continue;

            Scalar divide = Factory.createScalarFromInitialValue(1.0);
            divide.div(rref.get(i, j));

            rref.mulRow(i, divide);

            for (int k = i - 1; k >= 0; --k) {
                rref.mulRowAndAddOther(k, i,
                    Tensors.scalarMul(rref.get(k, j), Factory.createScalarFromInitialValue(-1.0)));
            }
        }

        return rref;
    }

    @Override
    public Matrix getInverse() throws CalculateNotSupportedException {
        checkIsSquare();

        int size = size().row;

        Matrix transpose = getTranspose();
        Scalar determinant = getDeterminant();
        checkIsReversible(determinant);

        Matrix cofactorMatrix = Factory.createMatrixFromInitialValue(size, size,
            Factory.createScalarFromInitialValue(0.0));

        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                Scalar sign = Factory.createScalarFromInitialValue(0.0);

                if (((i + j) % 2) == 0)
                    sign.set(1.0);
                else
                    sign.set(-1.0);

                cofactorMatrix.set(i, j, Tensors.scalarMul(sign, transpose.getMinor(i, j).getDeterminant()));
            }
        }

        Matrix inverse = cofactorMatrix;
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                inverse.get(i, j).div(determinant);
            }
        }

        return inverse;
    }

    private void checkIsReversible(Scalar determinant) throws CalculateNotSupportedException {
        if (determinant.equals(Factory.createScalarFromInitialValue(0.0)))
            throw new CalculateNotSupportedException("행렬이 가역이 아닙니다");
    }

    @Override
    public Scalar getDeterminant() throws CalculateNotSupportedException {
        checkIsSquare();

        Scalar res = Factory.createScalarFromInitialValue(0.0);

        int size = size().row;
        if (size == 1) {
            res.set(get(0, 0).get());
            return res;
        }

        Scalar zero = Factory.createScalarFromInitialValue(0.0);

        Scalar sign = Factory.createScalarFromInitialValue(1.0);
        Scalar minusOne = Factory.createScalarFromInitialValue(-1.0);
        for (int i = 0; i < size; ++i) {
            if (!zero.equals(get(0, i))) {
                Scalar signMulElement = Tensors.scalarMul(sign, get(0, i));

                res.add(Tensors.scalarMul(signMulElement, getMinor(0, i).getDeterminant()));
            }
            sign.mul(minusOne);
        }

        return res;
    }

    @Override
    public Matrix clone() {
        try {
            MatrixImpl cloned = (MatrixImpl) super.clone();
            cloned.data = (MatrixStorage) this.data.clone();

            return cloned;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");

        int size = this.size().row;
        for (int i = 0; i < size; i++) {
            result.append(this.getRow(i).toString());
            if (i != size - 1)
                result.append(", ");
        }

        result.append("]");

        return result.toString();
    }

    @Override
    public Size size() {
        return data.size();
    }

    @Override
    public void set(int row, int col, Scalar value) {
        data.set(row, col, value);
    }

    @Override
    public boolean isSquare() {
        return this.size().col == this.size().row;
    }

    @Override
    public boolean isUpperTriangular() throws CalculateNotSupportedException {
        return isUpperOrLowerTriangular(true);
    }

    @Override
    public boolean isLowerTriangular() throws CalculateNotSupportedException {
        return isUpperOrLowerTriangular(false);
    }

    private boolean isUpperOrLowerTriangular(boolean isUpper) throws CalculateNotSupportedException {
        checkIsSquare();

        Scalar zero = new ScalarImpl(0.0);

        int size = this.size().row;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (isUpper) {
                    if (i > j && !this.get(i, j).equals(zero))
                        return false;
                } else {
                    if (i < j && !this.get(i, j).equals(zero))
                        return false;
                }
            }
        }

        return true;
    }

    @Override
    public boolean isIdentity() throws CalculateNotSupportedException {
        checkIsSquare();

        Scalar zero = new ScalarImpl(0.0);
        Scalar one = new ScalarImpl(1.0);

        int size = this.size().row;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    if (!this.get(i, j).equals(one))
                        return false;
                } else {
                    if (!this.get(i, j).equals(zero))
                        return false;
                }
            }
        }

        return true;
    }

    private void checkIsSquare() throws CalculateNotSupportedException {
        if (!isSquare())
            throw new CalculateNotSupportedException("정사각 행렬이 아닙니다");
    }

    @Override
    public boolean isZero() {
        Scalar zero = new ScalarImpl(0.0);

        Size size = this.size();
        for (int i = 0; i < size.row; i++)
            for (int j = 0; j < size.col; j++)
                if (!this.get(i, j).equals(zero))
                    return false;
        return true;
    }

    @Override
    public boolean isRREF() {
        if (!isREF())
            return false;

        return isRREFButNotCheckREF();
    }

    private boolean isREF() {
        Size size = size();

        Scalar zero = new ScalarImpl(0.0);

        int firstNotZeroIndex = 0;
        for (int i = 0; i < size.row; ++i) {
            for (int j = 0; j < size.col; ++j) {
                if (!get(i, j).equals(zero)) {
                    if (firstNotZeroIndex > j)
                        return false;
                    else
                        firstNotZeroIndex = j;
                }
            }
        }

        return true;
    }

    private boolean isRREFButNotCheckREF() {
        Size size = size();

        Scalar zero = new ScalarImpl(0.0);
        Scalar one = new ScalarImpl(1.0);

        for (int i = 0; i < size.row; ++i) {
            for (int j = 0; j < size.col; ++j) {
                if (get(i, j).equals(one)) {
                    for (int k = 0; k < size.row; ++k) {
                        if (k != i)
                            if (!get(k, j).equals(zero))
                                return false;
                    }
                }
            }
        }

        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (!isSizeEqual((Matrix) obj))
            return false;

        Matrix.Size size = this.size();
        for (int i = 0; i < size.row; i++) {
            for (int j = 0; j < size.col; j++) {
                if (!this.get(i, j).equals(((Matrix) obj).get(i, j)))
                    return false;
            }
        }
        return true;
    }

    private boolean isSizeEqual(Matrix matrix) {
        return this.size().equals(matrix.size());
    }
}
