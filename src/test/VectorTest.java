package test;

import tensor.Factory;
import tensor.Tensors;
import tensor.Vector;

public class VectorTest extends BaseTester {
    VectorTest(String name) {
        super(name);

        addBasicFunctionTests();
        addCalculateTests();
    }

    private void addBasicFunctionTests() {
        addTest("특정 위치의 요소를 조회할 수 있다", () -> {
            int dummySize = 10;
            Vector dummy = Factory.createVectorFromInitialValue(dummySize, Factory.createScalarFromInitialValue(1.0));

            boolean isPassGetVectorValue = true;
            for (int i = 0; i < dummySize; ++i)
                if (!dummy.get(i).equals(Factory.createScalarFromInitialValue(1.0))) {
                    isPassGetVectorValue = false;
                    break;
                }
            assertTrue(isPassGetVectorValue, "백터의 모든 요소가 예상했던 것과 동일 하다");
        });

        addTest("특정 위치의 요소를 지정할 수 있다", () -> {
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
            assertTrue(isPassSetVectorValue, "백터의 모든 요소가 번경헀던 것과 동일 하다");
        });

        addTest("크기 정보를 조회할 수 있다", () -> {
            int dummySize = 10;
            Vector dummy = Factory.createVectorFromInitialValue(dummySize, Factory.createScalarFromInitialValue(1.0));

            assertEquals(dummy.size(), dummySize, "백터의 크기가 지정했던 것과 동일하다");
        });

        addTest("객체를 콘솔에 출력할 수 있다", () -> {
            int dummySize = 10;
            Vector dummy = Factory.createVectorFromInitialValue(dummySize, Factory.createScalarFromInitialValue(1.0));

            String dummyString = "[1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0]";
            assertEquals(dummy.toString(), dummyString, "toString의 결과가 예상했던 것과 동일하다");
        });

        addTest("객체의 동등성 판단을 할 수 있다", () -> {
            int dummySize = 10;
            Vector dummy = Factory.createVectorFromInitialValue(dummySize, Factory.createScalarFromInitialValue(1.0));

            assertEquals(dummy, Factory.createVectorFromInitialValue(dummy.size(), Factory.createScalarFromInitialValue(1.0)), "동일한 객체의 동등성 판단에 성공했다");
            assertTrue(!dummy.equals(Factory.createVectorFromInitialValue(dummy.size(), Factory.createScalarFromInitialValue(2.0))), "다른 객체의 동등성 판단에 성공했다");
        });

        addTest("객체 복제를 할 수 있다", () -> {
            int dummySize = 10;
            Vector dummy = Factory.createVectorFromInitialValue(dummySize, Factory.createScalarFromInitialValue(1.0));

            assertEquals(dummy, dummy.clone(), "복체된 객체와 원래 객체의 동등성 판단에 성공했다");
        });
    }

    private void addCalculateTests() {
        addTest("백터의 연산", () -> {
            int dummySize = 10;
            Vector dummy = Factory.createVectorFromInitialValue(dummySize, Factory.createScalarFromInitialValue(1.0));

            dummy.add(dummy);
            assertEquals(dummy, Factory.createVectorFromInitialValue(dummy.size(), Factory.createScalarFromInitialValue(2.0)), "벡터는 다른 벡터와 덧셈이 가능하다");
            dummy.clear(Factory.createScalarFromInitialValue(1.0));

            dummy.mul(Factory.createScalarFromInitialValue(2.0));
            assertEquals(dummy, Factory.createVectorFromInitialValue(dummy.size(), Factory.createScalarFromInitialValue(2.0)), "벡터는 다른 스칼라와 곱셈이 가능하다");
            dummy.clear(Factory.createScalarFromInitialValue(1.0));

            assertEquals(Tensors.vectorAdd(Factory.createVectorFromInitialValue(dummy.size(), Factory.createScalarFromInitialValue(1.0)), Factory.createVectorFromInitialValue(dummy.size(), Factory.createScalarFromInitialValue(1.0))), Factory.createVectorFromInitialValue(dummy.size(), Factory.createScalarFromInitialValue(2.0)), "벡터는 다른 벡터와 덧셈이 가능하다 (static)");

            assertEquals(Tensors.vectorMul(Factory.createVectorFromInitialValue(dummy.size(), Factory.createScalarFromInitialValue(1.0)), Factory.createScalarFromInitialValue(2.0)), Factory.createVectorFromInitialValue(dummy.size(), Factory.createScalarFromInitialValue(2.0)), "벡터는 다른 스칼라와 곱셈이 가능하다 (static)");
        });
    }

}
