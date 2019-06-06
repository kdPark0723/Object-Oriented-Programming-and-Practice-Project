package test;

import tensor.Factory;
import tensor.Scalar;
import tensor.WrongCSVFormatException;

class FactoryTest extends BaseTest {
    FactoryTest() {
        addScalarTests("스칼라의 생성 (default 접근 지정자 사용)");
        addVectorTests("벡터의 생성 (default 접근 지정자 사용)");
        addMatrixTests("행렬의 생성 (default 접근 지정자 사용)");
    }

    private void addScalarTests(String name) {
        BaseTester test = addTest(name);

        test.addTest("값(double 혹은 Double)을 지정하여 스칼라를 생성할 수 있다", () -> {
            Factory.createScalarFromInitialValue(1.0);
            test.assertTrue(true, "예외 없이 스칼라가 생성됨(double)");

            Factory.createScalarFromInitialValue(new Double(1.0));
            test.assertTrue(true, "예외 없이 스칼라가 생성됨(Double)");
        });

        test.addTest("i 이상 j 미만의 무작위 값을 요소로 하는 스칼라를 생성할 수 있다", () -> {
            Scalar scalarCreateByRandomValue = Factory.createScalarFromRandomValue(1.0, 100.0);
            test.assertTrue(true, "예외 없이 스칼라가 생성됨");
        });
    }

    private void addVectorTests(String name) {
        BaseTester test = addTest(name);

        test.addTest("지정한 하나의 값을 모든 요소의 값으로 하는 n-차원 벡터를 생성할 수 있다", () -> {
            Factory.createVectorFromInitialValue(10, Factory.createScalarFromInitialValue(1.0));
            test.assertTrue(true, "예외 없이 벡터가 생성됨");
        });

        test.addTest("i 이상 j 미만의 무작위 값을 요소로 하는 n-차원 벡터를 생성할 수 있다", () -> {
            Factory.createVectorFromRandomValue(10, Factory.createScalarFromInitialValue(1.0), Factory.createScalarFromInitialValue(100.0));
            test.assertTrue(true, "예외 없이 벡터가 생성됨");
        });

        test.addTest("1차원 배열로부터 n-차원 벡터를 생성할 수 있다", () -> {
            Scalar[] arr = new Scalar[10];
            Factory.createVectorFromArray(arr);
            test.assertTrue(true, "예외 없이 벡터가 생성됨");
        });
    }

    private void addMatrixTests(String name) {
        BaseTester test = addTest(name);

        test.addTest("지정한 하나의 값을 모든 요소의 값으로 하는 m x n 행렬을 생성할 수 있다", () -> {
            Factory.createMatrixFromInitialValue(10, 10, Factory.createScalarFromInitialValue(1.0));
            test.assertTrue(true, "예외 없이 행렬이 생성됨");
        });

        test.addTest("i 이상 j 미만의 무작위 값을 요소로 하는 m x n 행렬을 생성할 수 있다", () -> {
            Factory.createMatrixFromRandomValue(10, 10, Factory.createScalarFromInitialValue(1.0), Factory.createScalarFromInitialValue(100.0));
            test.assertTrue(true, "예외 없이 행렬이 생성됨");
        });

        test.addTest("csv 파일로부터 m x n 행렬을 생성할 수 있다", () -> {
            Factory.createMatrixFromCSVFile("src/test/passDummyMatrix.csv");
            test.assertTrue(true, "예외 없이 행렬이 생성됨");

            try {
                Factory.createMatrixFromCSVFile("src/test/failDummyMatrix.csv");
                test.assertTrue(false, "잘못된 형식에도 정상적으로 생성됨");
            } catch (WrongCSVFormatException e) {
                test.assertTrue(true, "잘못된 형식에 예외를 던짐");
            }
        });

        test.addTest("2차원 배열로부터 m x n 행렬을 생성할 수 있다", () -> {
            Factory.createMatrixFromArray(new Scalar[10][10]);
            test.assertTrue(true, "예외 없이 행렬이 생성됨");
        });

        test.addTest("단위 행렬을 생성할 수 있다", () -> {
            Factory.createIdentityMatrix(10);
            test.assertTrue(true, "예외 없이 행렬이 생성됨");
        });
    }
}
