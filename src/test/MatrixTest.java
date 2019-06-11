package test;

import tensor.*;

class MatrixTest extends BaseTest {
    MatrixTest() {
        super();

        addBasicFunctionTests("행렬의 기본 기능");
        addCalculateTests("행렬의 연산");
        addAdvancedFeatures("행렬의 고급 기능");
    }

    private void addBasicFunctionTests(String name) {
        BaseTester test = addTest(name);

        test.addTest("특정 위치의 요소를 조회할 수 있다", () -> {
            int dummySize = 10;
            Matrix dummy = Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(1.0));

            boolean isPassGetVectorValue = true;
            for (int i = 0; i < dummySize; ++i)
                for (int j = 0; j < dummySize; ++j)
                    if (!dummy.get(i, j).equals(Factory.createScalarFromInitialValue(1.0))) {
                        isPassGetVectorValue = false;
                        break;
                    }
            test.assertTrue(isPassGetVectorValue, "행렬의 모든 요소가 예상했던 것과 동일 하다");
        });

        test.addTest("특정 위치의 요소를 지정할 수 있다", () -> {
            int dummySize = 10;
            Matrix dummy = Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(1.0));

            for (int i = 0; i < dummySize; ++i)
                for (int j = 0; j < dummySize; ++j)
                    dummy.set(i, j, Factory.createScalarFromInitialValue(2.0));

            boolean isPassGetVectorValue = true;
            for (int i = 0; i < dummySize; ++i)
                for (int j = 0; j < dummySize; ++j)
                    if (!dummy.get(i, j).equals(Factory.createScalarFromInitialValue(2.0))) {
                        isPassGetVectorValue = false;
                        break;
                    }
            test.assertTrue(isPassGetVectorValue, "행렬의 모든 요소가 예상했던 것과 동일 하다");
        });

        test.addTest("크기 정보를 조회할 수 있다", () -> {
            int dummySize = 10;
            Matrix dummy = Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(1.0));

            test.assertEquals(dummy.size(), new Matrix.Size(dummySize, dummySize), "행렬의 크기가 지정했던 것과 동일하다");
        });

        test.addTest("객체를 콘솔에 출력할 수 있다", () -> {
            int dummySize = 4;
            Matrix dummy = Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(1.0));

            String dummyString = "[[1.0, 1.0, 1.0, 1.0], [1.0, 1.0, 1.0, 1.0], [1.0, 1.0, 1.0, 1.0], [1.0, 1.0, 1.0, 1.0]]";
            test.assertEquals(dummy.toString(), dummyString, "toString의 결과가 예상했던 것과 동일하다");
        });

        test.addTest("객체의 동등성 판단을 할 수 있다", () -> {
            int dummySize = 10;
            Matrix dummy = Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(1.0));

            test.assertEquals(dummy, Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(1.0)), "동일한 객체의 동등성 판단에 성공했다");
            test.assertTrue(!dummy.equals(Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(2.0))), "다른 객체의 동등성 판단에 성공했다");
        });

        test.addTest("객체 복제를 할 수 있다", () -> {
            int dummySize = 10;
            Matrix dummy = Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(1.0));
            Matrix cloned = dummy.clone();

            test.assertEquals(dummy, cloned, "복체된 객체와 원래 객체의 동등성 판단에 성공했다");

            cloned.set(0, 0, Factory.createScalarFromInitialValue(0.0));
            test.assertTrue(!dummy.equals(cloned), "복제된 객체와 기본의 객체가 다른 객체이다");
        });
    }

    private void addCalculateTests(String name) {
        BaseTester test = addTest(name);

        test.addTest("행렬은 다른 행렬과 덧셈이 가능하다. (크기가 같을 때)", () -> {
            int dummySize = 10;
            Matrix dummy1 = Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(1.0));
            Matrix dummy2 = Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(2.0));

            dummy1.add(dummy2);
            test.assertEquals(dummy1, Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(3.0)), "연산 결과가 예상과 같다");

            Matrix dummy3 = Factory.createMatrixFromInitialValue(dummySize + 10, dummySize, Factory.createScalarFromInitialValue(2.0));
            try {
                dummy3.add(dummy1);
                test.assertTrue(false, "행렬가 길이가 다를때 연산이 성공했다.");
            } catch (CalculateNotSupportedException e) {
                test.assertTrue(true, "행렬가 길이가 다를때 연산이 실패했다.");
            }
        });

        test.addTest("행렬은 다른 행렬과 곱셈이 가능하다. ((mxn)x(nxl)일 때)", () -> {
            int m = 10;
            int n = 20;
            int l = 30;
            Matrix dummy1 = Factory.createMatrixFromInitialValue(m, n, Factory.createScalarFromInitialValue(1.0));
            Matrix dummy2 = Factory.createMatrixFromInitialValue(n, l, Factory.createScalarFromInitialValue(2.0));

            dummy1.mulByRight(dummy2);
            test.assertEquals(dummy1, Factory.createMatrixFromInitialValue(m, l, Factory.createScalarFromInitialValue(2.0 * n)), "연산 결과가 예상과 같다 (오른쪽 행렬로서 곱하기)");

            Matrix dummy3 = Factory.createMatrixFromInitialValue(m, n, Factory.createScalarFromInitialValue(1.0));
            dummy2.mulByLeft(dummy3);
            test.assertEquals(dummy2, Factory.createMatrixFromInitialValue(m, l, Factory.createScalarFromInitialValue(2.0 * n)), "연산 결과가 예상과 같다 (왼쪽 행렬로서 곱하기)");

            Matrix dummy4 = Factory.createMatrixFromInitialValue(n + 10, l, Factory.createScalarFromInitialValue(2.0));
            try {
                dummy4.mulByLeft(dummy3);
                test.assertTrue(false, "행렬가 길이가 다를때 연산이 성공했다.");
            } catch (CalculateNotSupportedException e) {
                test.assertTrue(true, "행렬가 길이가 다를때 연산이 실패했다.");
            }
        });

        test.addTest("전달받은 두 벡터의 덧셈이 가능하다. (길이가 같을 때)", () -> {
            int dummySize = 10;
            Matrix dummy1 = Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(1.0));
            Matrix dummy2 = Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(2.0));

            test.assertEquals(Tensors.matrixAdd(dummy1, dummy2), Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(3.0)), "연산 결과가 예상과 같다");

            Matrix dummy3 = Factory.createMatrixFromInitialValue(dummySize + 10, dummySize, Factory.createScalarFromInitialValue(2.0));
            try {
                Tensors.matrixAdd(dummy1, dummy3);
                test.assertTrue(false, "행렬가 길이가 다를때 연산이 성공했다.");
            } catch (CalculateNotSupportedException e) {
                test.assertTrue(true, "행렬가 길이가 다를때 연산이 실패했다.");
            }
        });

        test.addTest("전달받은 두 행렬의 곱셈이 가능하다. ((mxn)x(nxl)일 때)", () -> {
            int m = 10;
            int n = 20;
            int l = 30;
            Matrix dummy1 = Factory.createMatrixFromInitialValue(m, n, Factory.createScalarFromInitialValue(1.0));
            Matrix dummy2 = Factory.createMatrixFromInitialValue(n, l, Factory.createScalarFromInitialValue(2.0));

            test.assertEquals(Tensors.matrixMul(dummy1, dummy2), Factory.createMatrixFromInitialValue(m, l, Factory.createScalarFromInitialValue(2.0 * n)), "연산 결과가 예상과 같다");

            Matrix dummy3 = Factory.createMatrixFromInitialValue(n + 10, l, Factory.createScalarFromInitialValue(2.0));
            try {
                Tensors.matrixMul(dummy1, dummy3);
                test.assertTrue(false, "행렬가 길이가 다를때 연산이 성공했다.");
            } catch (CalculateNotSupportedException e) {
                test.assertTrue(true, "행렬가 길이가 다를때 연산이 실패했다.");
            }
        });
    }

    private void addAdvancedFeatures(String name) {
        BaseTester test = addTest(name);

        test.addTest("행렬은 다른 행렬과 가로로 합쳐질 수 있다.", () -> {
            int dummySize = 10;
            Matrix dummy1 = Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(1.0));
            Matrix dummy2 = Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(1.0));

            dummy1.concatAsCol(dummy2);
            test.assertEquals(dummy1, Factory.createMatrixFromInitialValue(dummySize, dummySize * 2, Factory.createScalarFromInitialValue(1.0)), "연산 결과가 예상과 같다");

            Matrix dummy3 = Factory.createMatrixFromInitialValue(dummySize + 10, dummySize, Factory.createScalarFromInitialValue(1.0));
            try {
                dummy2.concatAsCol(dummy3);
                test.assertTrue(false, "행렬의 행의 크기가 다를때 연산에 성공했다.");
            } catch (CalculateNotSupportedException e) {
                test.assertTrue(true, "행렬의 행의 크기가 다를때 연산에 실패했다.");
            }
        });

        test.addTest("행렬은 다른 행렬과 세로로 합쳐질 수 있다. (두 행렬의 열 수가 같아야 가능)", () -> {
            int dummySize = 10;
            Matrix dummy1 = Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(1.0));
            Matrix dummy2 = Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(1.0));

            dummy1.concatAsRow(dummy2);
            test.assertEquals(dummy1, Factory.createMatrixFromInitialValue(dummySize * 2, dummySize, Factory.createScalarFromInitialValue(1.0)), "연산 결과가 예상과 같다");

            Matrix dummy3 = Factory.createMatrixFromInitialValue(dummySize, dummySize + 10, Factory.createScalarFromInitialValue(1.0));
            try {
                dummy2.concatAsRow(dummy3);
                test.assertTrue(false, "행렬의 열의 크기가 다를때 연산에 성공했다.");
            } catch (CalculateNotSupportedException e) {
                test.assertTrue(true, "행렬의 열의 크기가 다를때 연산에 실패했다.");
            }
        });

        test.addTest("행렬은 다른 행렬과 가로로 합쳐질 수 있다. static", () -> {
            int dummySize = 10;
            Matrix dummy1 = Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(1.0));
            Matrix dummy2 = Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(1.0));

            dummy1.concatAsCol(dummy2);
            test.assertEquals(dummy1, Tensors.matrixConcatAsCol(Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(1.0)), dummy2), "연산 결과가 예상과 같다 tensor");
        });

        test.addTest("행렬은 다른 행렬과 세로로 합쳐질 수 있다. static", () -> {
            int dummySize = 10;
            Matrix dummy1 = Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(1.0));
            Matrix dummy2 = Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(1.0));

            dummy1.concatAsRow(dummy2);
            test.assertEquals(dummy1, Tensors.matrixConcatAsRow(Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(1.0)), dummy2), "연산 결과가 예상과 같다 tensor");
        });

        test.addTest("행렬은 특정 행을 벡터 형태로 추출해 줄 수 있다. (행 벡터 추출)", () -> {
            int dummySize = 10;
            Matrix dummy = Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(0.0));
            for (int i = 0; i < dummySize; i++)
                dummy.set(i, 0, Factory.createScalarFromInitialValue((double) i));

            Vector row = Factory.createVectorFromInitialValue(dummySize, Factory.createScalarFromInitialValue(0.0));

            test.assertEquals(row, dummy.getRow(0), "추출된 백터가 예상과 같다");
        });

        test.addTest("행렬은 특정 열을 벡터 형태로 추출해 줄 수 있다. (열 벡터 추출)", () -> {
            int dummySize = 10;
            Matrix dummy = Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(0.0));
            for (int i = 0; i < dummySize; i++)
                dummy.set(0, i, Factory.createScalarFromInitialValue((double) i));

            Vector col = Factory.createVectorFromInitialValue(dummySize, Factory.createScalarFromInitialValue(0.0));

            test.assertEquals(col, dummy.getCol(0), "추출된 백터가 예상과 같다");
        });

        test.addTest("행렬은 특정 범위의 부분 행렬을 추출해 줄 수 있다. 시작과 끝 행 인덱스, 시작과 끝 열 인덱스를 사용하여 범위 지정", () -> {
            int dummySize = 10;
            int surStart = 2;
            int subSize = 5;
            Matrix dummy = Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(0.0));
            for (int i = surStart; i < subSize + surStart; i++)
                for (int j = surStart; j < subSize + surStart; j++)
                    dummy.set(i, j, Factory.createScalarFromInitialValue(1.0));

            test.assertEquals(dummy.getSub(new Matrix.Range(surStart, subSize + surStart), new Matrix.Range(surStart, subSize + surStart)),
                Factory.createMatrixFromInitialValue(subSize, subSize, Factory.createScalarFromInitialValue(1.0)),
                "추출된 부분 행렬이 예상과 같다");

        });

        test.addTest("행렬은 특정 범위의 부분 행렬을 추출해 줄 수 있다. 특정 하나의 행과 하나의 열을 제외한 부분 행렬 (minor)", () -> {
            int dummySize = 10;
            int minorRow = 2;
            int minorCol = 4;
            Matrix dummy = Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(0.0));
            for (int i = 0; i < dummySize; i++)
                dummy.set(i, minorCol, Factory.createScalarFromInitialValue(1.0));
            for (int i = 0; i < dummySize; i++)
                dummy.set(minorRow, i, Factory.createScalarFromInitialValue(1.0));

            test.assertEquals(dummy.getMinor(minorRow, minorCol),
                Factory.createMatrixFromInitialValue(dummySize - 1, dummySize - 1, Factory.createScalarFromInitialValue(0.0)),
                "추출된 부분 행렬이 예상과 같다");
        });

        test.addTest("행렬은 전치행렬을 (새로 생성하여) 구해줄 수 있다", () -> {
            int dummySize = 10;
            Matrix dummy = Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(0.0));

            for (int i = 0; i < dummySize; i++)
                for (int j = 0; j < dummySize; j++)
                    if (i < j)
                        dummy.set(i, j, Factory.createScalarFromInitialValue(1.0));

            Matrix transpose = Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(0.0));
            for (int i = 0; i < dummySize; i++)
                for (int j = 0; j < dummySize; j++)
                    if (i > j)
                        transpose.set(i, j, Factory.createScalarFromInitialValue(1.0));

            test.assertEquals(dummy.getTranspose(), transpose,
                "얻은 전치 행렬이 예상과 같다");
        });

        test.addTest("행렬은 대각 요소의 합을 구해줄 수 있다", () -> {
            int dummySize = 10;
            Matrix dummy = Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(1.0));

            test.assertEquals(dummy.getTrace(), Factory.createScalarFromInitialValue(1.0 * dummySize),
                "얻은 대각 요소의 합이 예상과 같다");
        });

        test.addTest("행렬은 자신이 정사각 행렬인지 여부를 판별해 줄 수 있다.", () -> {
            int dummySize = 10;

            test.assertEquals(
                Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(1.0)).isSquare(),
                true,
                "정사각 행렬일때 true가 나왔다");
            test.assertEquals(
                Factory.createMatrixFromInitialValue(dummySize + 1, dummySize, Factory.createScalarFromInitialValue(1.0)).isSquare(),
                false,
                "정사각 행렬이 아닐때 false가 나왔다");
        });

        test.addTest("행렬은 자신이 상삼각 행렬인지 여부를 판별해 줄 수 있다. (nxn 행렬)", () -> {
            int dummySize = 10;
            Matrix triangular = Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(0.0));

            for (int i = 0; i < dummySize; ++i)
                for (int j = 0; j < dummySize; ++j)
                    if (i < j)
                        triangular.set(i, j, Factory.createScalarFromInitialValue(1.0));
            test.assertEquals(triangular.isUpperTriangular(), true, "상삼각 행렬일때 참이 나왔다.");

            triangular.set(dummySize - 1, 0, Factory.createScalarFromInitialValue(1.0));
            test.assertEquals(triangular.isUpperTriangular(), false, "상삼각 행렬이 아닐때 거짓이 나왔다.");

            Matrix dummy = Factory.createMatrixFromInitialValue(dummySize, dummySize + 10, Factory.createScalarFromInitialValue(1.0));
            try {
                dummy.isUpperTriangular();
                test.assertTrue(false, "사각 향렬일때 연산에 성공했다.");
            } catch (CalculateNotSupportedException e) {
                test.assertTrue(true, "사각 향렬이 아닐때 연산에 실패했다.");
            }
        });

        test.addTest("행렬은 자신이 하삼각 행렬인지 여부를 판별해 줄 수 있다. (nxn 행렬)", () -> {
            int dummySize = 10;
            Matrix triangular = Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(0.0));

            for (int i = 0; i < dummySize; ++i)
                for (int j = 0; j < dummySize; ++j)
                    if (i > j)
                        triangular.set(i, j, Factory.createScalarFromInitialValue(1.0));
            test.assertEquals(triangular.isLowerTriangular(), true, "하삼각 행렬일때 참이 나왔다.");

            triangular.set(0, dummySize - 1, Factory.createScalarFromInitialValue(1.0));
            test.assertEquals(triangular.isLowerTriangular(), false, "하삼각 행렬이 아닐때 거짓이 나왔다.");

            Matrix dummy = Factory.createMatrixFromInitialValue(dummySize, dummySize + 10, Factory.createScalarFromInitialValue(1.0));
            try {
                dummy.isLowerTriangular();
                test.assertTrue(false, "사각 향렬일때 연산에 성공했다.");
            } catch (CalculateNotSupportedException e) {
                test.assertTrue(true, "사각 향렬이 아닐때 연산에 실패했다.");
            }
        });

        test.addTest("행렬은 자신이 단위 행렬인지 여부를 판별해 줄 수 있다. (nxn 행렬)", () -> {
            int dummySize = 10;
            Matrix dummy = Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(0.0));

            for (int i = 0; i < dummySize; ++i)
                dummy.set(i, i, Factory.createScalarFromInitialValue(1.0));
            test.assertEquals(dummy.isIdentity(), true, "단위 행렬일때 참이 나왔다.");

            dummy.set(dummySize - 1, 0, Factory.createScalarFromInitialValue(1.0));
            test.assertEquals(dummy.isIdentity(), false, "단위 행렬이 아닐때 거짓이 나왔다.");

            Matrix dummy2 = Factory.createMatrixFromInitialValue(dummySize, dummySize + 10, Factory.createScalarFromInitialValue(1.0));
            try {
                dummy2.isIdentity();
                test.assertTrue(false, "사각 향렬일때 연산에 성공했다.");
            } catch (CalculateNotSupportedException e) {
                test.assertTrue(true, "사각 향렬이 아닐때 연산에 실패했다.");
            }
        });

        test.addTest("행렬은 자신이 영 행렬인지 여부를 판별해 줄 수 있다. (zero matrix)", () -> {
            int dummySize = 10;
            Matrix dummy = Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(0.0));

            test.assertEquals(dummy.isZero(), true, "영 행렬일때 참이 나왔다.");

            dummy.set(dummySize - 1, 0, Factory.createScalarFromInitialValue(1.0));
            test.assertEquals(dummy.isZero(), false, "영 행렬이 아닐때 거짓이 나왔다.");
        });

        test.addTest("행렬은 특정 두 행의 위치를 맞교환할 수 있다.", () -> {
            int dummySize = 10;
            Matrix dummy = Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(0.0));

            for (int i = 0; i < dummySize; ++i)
                dummy.set(0, i, Factory.createScalarFromInitialValue(1.0));

            dummy.swapRow(0, 1);

            test.assertEquals(dummy.getRow(0),
                Factory.createVectorFromInitialValue(dummySize, Factory.createScalarFromInitialValue(0.0)),
                "한 행의 값이 바뀌었다. (1)");
            test.assertEquals(dummy.getRow(1),
                Factory.createVectorFromInitialValue(dummySize, Factory.createScalarFromInitialValue(1.0)),
                "한 행의 값이 바뀌었다. (2)");
        });

        test.addTest("행렬은 특정 두 열의 위치를 맞교환할 수 있다.", () -> {
            int dummySize = 10;
            Matrix dummy = Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(0.0));

            for (int i = 0; i < dummySize; ++i)
                dummy.set(i, 0, Factory.createScalarFromInitialValue(1.0));

            dummy.swapCol(0, 1);

            test.assertEquals(dummy.getCol(0),
                Factory.createVectorFromInitialValue(dummySize, Factory.createScalarFromInitialValue(0.0)),
                "한 열의 값이 바뀌었다. (1)");
            test.assertEquals(dummy.getCol(1),
                Factory.createVectorFromInitialValue(dummySize, Factory.createScalarFromInitialValue(1.0)),
                "한 열의 값이 바뀌었다. (2)");
        });

        test.addTest("행렬은 특정 행에 상수배(스칼라)를 할 수 있다.", () -> {
            int dummySize = 10;
            Matrix dummy = Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(1.0));

            dummy.mulRow(0, Factory.createScalarFromInitialValue(0.0));
            test.assertEquals(dummy.getRow(0),
                Factory.createVectorFromInitialValue(dummySize, Factory.createScalarFromInitialValue(0.0)),
                "연산의 결과가 예상과 같다");
        });

        test.addTest("행렬은 특정 열에 상수배(스칼라)를 할 수 있다.", () -> {
            int dummySize = 10;
            Matrix dummy = Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(1.0));

            dummy.mulCol(0, Factory.createScalarFromInitialValue(0.0));
            test.assertEquals(dummy.getCol(0),
                Factory.createVectorFromInitialValue(dummySize, Factory.createScalarFromInitialValue(0.0)),
                "연산의 결과가 예상과 같다");
        });

        test.addTest("행렬은 특정 행에 다른 행의 상수배를 더할 수 있다.", () -> {
            int dummySize = 10;
            Matrix dummy = Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(1.0));

            dummy.mulRowAndAddOther(0, 1, Factory.createScalarFromInitialValue(2.0));
            test.assertEquals(dummy.getRow(0),
                Factory.createVectorFromInitialValue(dummySize, Factory.createScalarFromInitialValue(3.0)),
                "연산의 결과가 예상과 같다");
        });

        test.addTest("행렬은 특정 열에 다른 열의 상수배를 더할 수 있다.", () -> {
            int dummySize = 10;
            Matrix dummy = Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(1.0));

            dummy.mulColAndAddOther(0, 1, Factory.createScalarFromInitialValue(2.0));
            test.assertEquals(dummy.getCol(0),
                Factory.createVectorFromInitialValue(dummySize, Factory.createScalarFromInitialValue(3.0)),
                "연산의 결과가 예상과 같다");
        });

        test.addTest("행렬은 자신으로부터 RREF 행렬을 구해서 반환해 줄 수 있다. (row reduced echelon form).", () -> {
            int dummySize = 4;
            Matrix dummy = Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(0.0));

            // 0 2 0 3
            // 0 14 9 1
            // 12 2 0 1
            // 1 2 9 4
            dummy.set(0, 1, Factory.createScalarFromInitialValue(2.0));
            dummy.set(0, 3, Factory.createScalarFromInitialValue(3.0));

            dummy.set(1, 1, Factory.createScalarFromInitialValue(14.0));
            dummy.set(1, 2, Factory.createScalarFromInitialValue(9.0));
            dummy.set(1, 3, Factory.createScalarFromInitialValue(1.0));

            dummy.set(2, 0, Factory.createScalarFromInitialValue(12.0));
            dummy.set(2, 1, Factory.createScalarFromInitialValue(2.0));
            dummy.set(2, 3, Factory.createScalarFromInitialValue(1.0));

            dummy.set(3, 0, Factory.createScalarFromInitialValue(1.0));
            dummy.set(3, 1, Factory.createScalarFromInitialValue(2.0));
            dummy.set(3, 2, Factory.createScalarFromInitialValue(9.0));
            dummy.set(3, 3, Factory.createScalarFromInitialValue(4.0));

            Matrix rref = dummy.getRREF();

            test.assertTrue(rref.isIdentity(), "연산 결과가 예상과 같습니다");
        });

        test.addTest("행렬은 자신이 RREF 행렬인지 여부를 판별해 줄 수 있다.", () -> {
            int dummySize = 4;
            Matrix dummy = Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(1.0));
            test.assertTrue(!dummy.isRREF(), "연산 결과가 예상과 같습니다");

            Matrix rref = dummy.getRREF();

            test.assertTrue(rref.isRREF(), "연산 결과가 예상과 같습니다");
        });

        test.addTest("행렬은 자신의 행렬식을 구해줄 수 있다. (nxn 행렬) (determinant).", () -> {
            int dummySize = 10;
            Matrix dummy = Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(0.0));

            for (int i = 0; i < dummySize; i++) {
                dummy.set(i, i, Factory.createScalarFromInitialValue(2.0));
            }

            test.assertEquals(dummy.getDeterminant(), Factory.createScalarFromInitialValue(1024.0), "연산 결과가 예상과 같습니다");
        });

        test.addTest("행렬은 자신의 역행렬을 구해줄 수 있다. (nxn 행렬)", () -> {
            int dummySize = 3;
            Matrix dummy = Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(0.0));

            // 1 2 3
            // 2 5 3
            // 1 0 8
            dummy.set(0, 0, Factory.createScalarFromInitialValue(1.0));
            dummy.set(0, 1, Factory.createScalarFromInitialValue(2.0));
            dummy.set(0, 2, Factory.createScalarFromInitialValue(3.0));

            dummy.set(1, 0, Factory.createScalarFromInitialValue(2.0));
            dummy.set(1, 1, Factory.createScalarFromInitialValue(5.0));
            dummy.set(1, 2, Factory.createScalarFromInitialValue(3.0));

            dummy.set(2, 0, Factory.createScalarFromInitialValue(1.0));
            dummy.set(2, 1, Factory.createScalarFromInitialValue(0.0));
            dummy.set(2, 2, Factory.createScalarFromInitialValue(8.0));

            Matrix inverse = Factory.createMatrixFromInitialValue(dummySize, dummySize, Factory.createScalarFromInitialValue(0.0));

            // -40 16 9
            // 13 -5 -3
            // 5 -2 -1
            inverse.set(0, 0, Factory.createScalarFromInitialValue(-40.0));
            inverse.set(0, 1, Factory.createScalarFromInitialValue(16.0));
            inverse.set(0, 2, Factory.createScalarFromInitialValue(9.0));

            inverse.set(1, 0, Factory.createScalarFromInitialValue(13.0));
            inverse.set(1, 1, Factory.createScalarFromInitialValue(-5.0));
            inverse.set(1, 2, Factory.createScalarFromInitialValue(-3.0));

            inverse.set(2, 0, Factory.createScalarFromInitialValue(5.0));
            inverse.set(2, 1, Factory.createScalarFromInitialValue(-2.0));
            inverse.set(2, 2, Factory.createScalarFromInitialValue(-1.0));

            test.assertEquals(dummy.getInverse(), inverse, "연산 결과가 예상과 같습니다");
        });
    }
}
