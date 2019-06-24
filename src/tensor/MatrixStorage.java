package tensor;

import java.util.ArrayList;
import java.util.Arrays;

public class MatrixStorage implements Cloneable {
    private ArrayList<ArrayList<Scalar>> data;

    MatrixStorage(int n, int m, Scalar initValue) {
        data = new ArrayList<>(n);
        for (int i = 0; i < n; ++i)
            data.add(new ArrayList<>(m));
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; j++)
                data.get(i).add(initValue.clone());
    }

    MatrixStorage(ArrayList<ArrayList<Scalar>> array) {
        data = array;
    }

    MatrixStorage(Scalar[][] array) {
        data = new ArrayList<>(array.length);
        for (Scalar[] scalars : array) {
            data.add(new ArrayList<>(Arrays.asList(scalars)));
        }
    }

    Scalar get(int row, int col) throws IndexOutOfBoundsException {
        if (col < 0 || row < 0 || col >= size().col || row >= size().row)
            throw new IndexOutOfBoundsException("row: " + row + ", col:" + col);

        return data.get(row).get(col);
    }

    void set(int row, int col, Scalar value) throws IndexOutOfBoundsException {
        if (col < 0 || row < 0 || col >= size().col || row >= size().row)
            throw new IndexOutOfBoundsException("row: " + row + ", col:" + col);

        data.get(row).get(col).set(value.get());
    }

    Matrix.Size size() {
        return new Matrix.Size(data.size(), data.get(0).size());
    }

    void clear(Scalar value) {
        for (ArrayList<Scalar> datum : data) {
            for (Scalar scalar : datum) {
                scalar.set(value.get());
            }
        }
    }

    @Override
    public Object clone() {
        Scalar[][] scalars = new Scalar[this.size().row][this.size().col];

        for (int i = 0; i < scalars.length; i++)
            for (int j = 0; j < scalars[i].length; j++)
                scalars[i][j] = this.get(i, j).clone();

        return new MatrixStorage(scalars);
    }
}
