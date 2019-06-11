package test;

import tensor.CalculateNotSupportedException;
import tensor.Factory;
import tensor.Tensors;
import tensor.Vector;

class VectorTest extends BaseTest {
    VectorTest() {
        super();

        addBasicFunctionTests("벡터의 기본 기능");
        addCalculateTests("백터의 연산");
        addAdvancedFeatures("벡터의 고급 기능");
    }

    private void addBasicFunctionTests(String name) {
        BaseTester test = addTest(name);

        test.addTest("특정 위치의 요소를 조회할 수 있다", () -> {
            int dummySize = 10;
            Vector dummy = Factory.createVectorFromInitialValue(dummySize, Factory.createScalarFromInitialValue(1.0));

            boolean isPassGetVectorValue = true;
            for (int i = 0; i < dummySize; ++i)
                if (!dummy.get(i).equals(Factory.createScalarFromInitialValue(1.0))) {
                    isPassGetVectorValue = false;
                    break;
                }
            test.assertTrue(isPassGetVectorValue, "백터의 모든 요소가 예상했던 것과 동일 하다");
        });

        test.addTest("특정 위치의 요소를 지정할 수 있다", () -> {
            int dummySize = 10;
            Vector dummy = Factory.createVectorFromInitialValue(dummySize, Factory.createScalarFromInitialValue(1.0));

            boolean isPassSetVectorValue = true;
            for (int i = 0; i < dummySize; ++i)
                dummy.set(i, Factory.createScalarFromInitialValue((double)i));
            for (int i = 0; i < dummySize; ++i)
                if (!dummy.get(i).equals(Factory.createScalarFromInitialValue((double)i))) {
                    isPassSetVectorValue = false;
                    break;
                }
            test.assertTrue(isPassSetVectorValue, "백터의 모든 요소가 번경헀던 것과 동일 하다");
        });

        test.addTest("크기 정보를 조회할 수 있다", () -> {
            int dummySize = 10;
            Vector dummy = Factory.createVectorFromInitialValue(dummySize, Factory.createScalarFromInitialValue(1.0));

            test.assertEquals(dummy.size(), dummySize, "백터의 크기가 지정했던 것과 동일하다");
        });

        test.addTest("객체를 콘솔에 출력할 수 있다", () -> {
            int dummySize = 10;
            Vector dummy = Factory.createVectorFromInitialValue(dummySize, Factory.createScalarFromInitialValue(1.0));

            String dummyString = "[1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0]";
            test.assertEquals(dummy.toString(), dummyString, "toString의 결과가 예상했던 것과 동일하다");
        });

        test.addTest("객체의 동등성 판단을 할 수 있다", () -> {
            int dummySize = 10;
            Vector dummy = Factory.createVectorFromInitialValue(dummySize, Factory.createScalarFromInitialValue(1.0));

            test.assertEquals(dummy, Factory.createVectorFromInitialValue(dummy.size(), Factory.createScalarFromInitialValue(1.0)), "동일한 객체의 동등성 판단에 성공했다");
            test.assertTrue(!dummy.equals(Factory.createVectorFromInitialValue(dummy.size(), Factory.createScalarFromInitialValue(2.0))), "다른 객체의 동등성 판단에 성공했다");
        });

        test.addTest("객체 복제를 할 수 있다", () -> {
            int dummySize = 10;
            Vector dummy = Factory.createVectorFromInitialValue(dummySize, Factory.createScalarFromInitialValue(1.0));
            Vector cloned = dummy.clone();

            test.assertEquals(dummy, cloned, "복체된 객체와 원래 객체의 동등성 판단에 성공했다");

            cloned.set(0, Factory.createScalarFromInitialValue(0.0));
            test.assertTrue(!dummy.equals(cloned), "복제된 객체와 기본의 객체가 다른 객체이다");
        });
    }

    private void addCalculateTests(String name) {
        BaseTester test = addTest(name);

        test.addTest("벡터는 다른 벡터와 덧셈이 가능하다. (길이가 같을 때)", () -> {
            int dummySize = 10;
            Vector dummy1 = Factory.createVectorFromInitialValue(dummySize, Factory.createScalarFromInitialValue(1.0));
            Vector dummy2 = Factory.createVectorFromInitialValue(dummySize, Factory.createScalarFromInitialValue(2.0));

            dummy1.add(dummy2);

            test.assertEquals(dummy1, Factory.createVectorFromInitialValue(dummySize, Factory.createScalarFromInitialValue(3.0)), "연산 결과가 예상과 같다");

            Vector dummy3 = Factory.createVectorFromInitialValue(dummySize + 10, Factory.createScalarFromInitialValue(2.0));
            try {
                dummy3.add(dummy1);
                test.assertTrue(false, "벡터가 길이가 다를때 연산이 성공했다.");
            } catch (CalculateNotSupportedException e) {
                test.assertTrue(true, "벡터가 길이가 다를때 연산이 실패했다.");
            }
        });

        test.addTest("벡터는 다른 스칼라와 곱셈이 가능하다.", () -> {
            int dummySize = 10;
            Vector dummy1 = Factory.createVectorFromInitialValue(dummySize, Factory.createScalarFromInitialValue(1.0));

            dummy1.mul(Factory.createScalarFromInitialValue(2.0));

            test.assertEquals(dummy1, Factory.createVectorFromInitialValue(dummySize, Factory.createScalarFromInitialValue(2.0)), "연산 결과가 예상과 같다");
        });

        test.addTest("전달받은 두 벡터의 덧셈이 가능하다. (길이가 같을 때)", () -> {
            int dummySize = 10;
            test.assertEquals(Tensors.vectorAdd(Factory.createVectorFromInitialValue(dummySize, Factory.createScalarFromInitialValue(1.0)),
                Factory.createVectorFromInitialValue(dummySize, Factory.createScalarFromInitialValue(1.0))),
                Factory.createVectorFromInitialValue(dummySize, Factory.createScalarFromInitialValue(2.0)),
                "벡터의 계산 결과가 예상과 같다");
        });

        test.addTest("전달받은 스칼라와 벡터의 곱셈이 가능하다. (벡터의 모든 요소에 스칼라를 곱한다.)", () -> {
            int dummySize = 10;
            test.assertEquals(Tensors.vectorMul(Factory.createVectorFromInitialValue(dummySize, Factory.createScalarFromInitialValue(1.0)),
                Factory.createScalarFromInitialValue(2.0)),
                Factory.createVectorFromInitialValue(dummySize, Factory.createScalarFromInitialValue(2.0)),
                "벡터의 계산 결과가 예상과 같다");
        });
    }

    private void addAdvancedFeatures(String name) {
        BaseTester test = addTest(name);

        test.addTest("n-차원 벡터 객체는 자신으로부터 nx1 행렬을 생성하여 반환할 수 있다.", () -> {
            int dummySize = 10;
            Vector dummy = Factory.createVectorFromInitialValue(dummySize, Factory.createScalarFromInitialValue(1.0));

            test.assertEquals(dummy.toMatrixNx1(), Factory.createMatrixFromInitialValue(dummySize, 1, Factory.createScalarFromInitialValue(1.0)), "생성 결과가 예상과 같다");
        });

        test.addTest("n-차원 벡터 객체는 자신으로부터 1xn 행렬을 생성하여 반환할 수 있다.", () -> {
            int dummySize = 10;
            Vector dummy = Factory.createVectorFromInitialValue(dummySize, Factory.createScalarFromInitialValue(1.0));

            test.assertEquals(dummy.toMatrix1xN(), Factory.createMatrixFromInitialValue(1, dummySize, Factory.createScalarFromInitialValue(1.0)), "생성 결과가 예상과 같다");
        });
    }
}
