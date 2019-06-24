package tensor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class VectorImpl implements Vector {
    private List<Scalar> data;

    VectorImpl(int size, Scalar initValue) throws SizeLessThanZeroException {
        checkSize(size);

        data = new ArrayList<>(size);

        for (int i = 0; i < size; ++i)
            data.add(Factory.createScalarFromInitialValue(initValue.get()));
    }

    VectorImpl(int size, Scalar minValue, Scalar maxValue) throws SizeLessThanZeroException {
        checkSize(size);

        data = new ArrayList<>(size);

        double min = minValue.get();
        double max = maxValue.get();

        for (int i = 0; i < size; ++i)
            data.add(Factory.createScalarFromInitialValue(Util.getRandomValue(min, max)));
    }

    VectorImpl(Scalar[] array) {
        data = new ArrayList<>(Arrays.asList(array));
    }

    static Vector add(Vector lhs, Vector rhs) throws CalculateNotSupportedException {
        Vector result = lhs.clone();
        result.add(rhs);

        return result;
    }

    static Vector mul(Vector lhs, Scalar rhs) {

        Vector result = lhs.clone();
        result.mul(rhs);
        return result;

    }

    private void checkSize(int size) throws SizeLessThanZeroException {
        if (size < 0)
            throw new SizeLessThanZeroException("크기가 0보다 작습니다.");
    }

    @Override
    public void add(Vector rhs) throws CalculateNotSupportedException {
        checkSizeEqual(rhs.size());

        int size = this.size();
        for (int i = 0; i < size; i++)
            this.get(i).add(rhs.get(i));
    }

    private void checkSizeEqual(int size) throws CalculateNotSupportedException {
        if (this.size() != size)
            throw new CalculateNotSupportedException("Vector size isn't same.");
    }

    @Override
    public void mul(Scalar rhs) {
        int size = this.size();
        for (int i = 0; i < size; i++)
            this.get(i).mul(rhs);
    }

    @Override
    public Scalar get(int index) throws IndexOutOfBoundsException {
        checkBound(index);

        return data.get(index);
    }

    @Override
    public void set(int index, Scalar value) throws IndexOutOfBoundsException {
        checkBound(index);

        data.set(index, value);
    }

    private void checkBound(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException();
    }

    @Override
    public Vector clone() {
        try {
            VectorImpl cloned = (VectorImpl) super.clone();
            cloned.data = (List<Scalar>) ((ArrayList<Scalar>) this.data).clone();

            return cloned;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public Matrix toMatrix1xN() {
        Matrix matrix = new MatrixImpl(1, this.size(), new ScalarImpl(0.0));
        for (int i = 0; i < this.size(); i++)
            matrix.set(0, i, this.get(i));

        return matrix;
    }

    @Override
    public Matrix toMatrixNx1() {
        Matrix matrix = new MatrixImpl(this.size(), 1, new ScalarImpl(0.0));
        for (int i = 0; i < this.size(); i++)
            matrix.set(i, 0, this.get(i));

        return matrix;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");

        int size = this.size();
        for (int i = 0; i < size; i++) {
            result.append(this.get(i).toString());
            if (i != size - 1)
                result.append(", ");
        }

        result.append("]");

        return result.toString();
    }

    @Override
    public boolean equals(Object obj) {
        Vector vector = (Vector) obj;

        boolean isEqual = true;
        if (vector.size() != this.size())
            isEqual = false;

        int size = this.size();
        for (int i = 0; i < size; ++i)
            if (!this.get(i).equals(vector.get(i))) {
                isEqual = false;
                break;
            }

        return isEqual;
    }
}
