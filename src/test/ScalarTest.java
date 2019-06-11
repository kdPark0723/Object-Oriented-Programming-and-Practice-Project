package test;

import tensor.Factory;
import tensor.Scalar;
import tensor.Tensors;

class ScalarTest extends BaseTest {
    ScalarTest() {
        addBasicFunctionTests("스칼라의 기본 기능");
        addCalculateTests("스칼라의 연산");
    }

    private void addBasicFunctionTests(String name) {
        BaseTester test = addTest(name);

        test.addTest("특정 위치의 요소를 지정/조회할 수 있다", () -> {
            Scalar dummy = Factory.createScalarFromInitialValue(1.0);
            test.assertEquals(dummy.get(), 1.0, "값을 성공적으로 조회했다");

            dummy.set(2.0);
            test.assertEquals(dummy.get(), 2.0, "값이 바뀐것을 확인했다");
        });

        test.addTest("객체를 콘솔에 출력할 수 있다", () -> {
            Scalar dummy = Factory.createScalarFromInitialValue(1.0);
            test.assertEquals(dummy.toString(), "1.0", "toString의 결과 값이 예측한 것과 동일하다");
        });

        test.addTest("객체의 동등성 판단을 할 수 있다", () -> {
            Scalar dummy = Factory.createScalarFromInitialValue(1.0);
            test.assertEquals(dummy, Factory.createScalarFromInitialValue(1.0), "동일한 값을 같는 스칼라가 동등성 판단에 통과 했다");
            test.assertTrue(!dummy.equals(Factory.createScalarFromInitialValue(2.0)), "다른 값을 같는 스칼라가 동등성 판단에 통과 했다");
        });

        test.addTest("값의 대소 비교를 할 수 있다", () -> {
            Scalar dummy = Factory.createScalarFromInitialValue(1.0);
            test.assertEquals(dummy.compareTo(Factory.createScalarFromInitialValue(2.0)), 1, "더 큰 값과 compareTo 연산을 했을때 결과가 1인 것을 확인 할 수 있다");
            test.assertEquals(dummy.compareTo(Factory.createScalarFromInitialValue(1.0)), 0, "동일 값과 compareTo 연산을 했을때 결과가 0인 것을 확인 할 수 있다");
            test.assertEquals(dummy.compareTo(Factory.createScalarFromInitialValue(0.0)), -1, "더 작은 값과 compareTo 연산을 했을때 결과가 -1인 것을 확인 할 수 있다");

        });

        test.addTest("객체 복제를 할 수 있다", () -> {
            Scalar dummy = Factory.createScalarFromInitialValue(1.0);
            Scalar cloned = dummy.clone();

            test.assertEquals(dummy, cloned, "복제된 객체와 기본의 객체가 동등성 판단에 성공했다");

            cloned.set(0.0);
            test.assertTrue(!dummy.equals(cloned), "복제된 객체와 기본의 객체가 다른 객체이다");
        });
    }

    private void addCalculateTests(String name) {
        BaseTester test = addTest(name);

        test.addTest("스칼라는 다른 스칼라와 덧셈이 가능하다", () -> {
            Scalar dummy = Factory.createScalarFromInitialValue(1.0);

            dummy.add(Factory.createScalarFromInitialValue(1.0));
            test.assertEquals(dummy, Factory.createScalarFromInitialValue(2.0), "스칼라의 덧셈의 결과가 예상하던 것과 같다");
        });

        test.addTest("스칼라는 다른 스칼라와 곱셈이 가능하다", () -> {
            Scalar dummy = Factory.createScalarFromInitialValue(1.0);

            dummy.mul(Factory.createScalarFromInitialValue(2.0));
            test.assertEquals(dummy, Factory.createScalarFromInitialValue(2.0), "스칼라의 곱셈의 결과가 예상하던 것과 같다");
        });

        test.addTest("전달받은 두 스칼라의 덧셈이 가능하다", () -> {
            Scalar dummy = Factory.createScalarFromInitialValue(1.0);

            test.assertEquals(Tensors.scalarAdd(dummy, Factory.createScalarFromInitialValue(1.0)), Factory.createScalarFromInitialValue(2.0), "스칼라의 덧셈의 결과가 예상하던 것과 같다");

        });

        test.addTest("전달받은 두 스칼라의 곱셈이 가능하다", () -> {
            Scalar dummy = Factory.createScalarFromInitialValue(1.0);

            test.assertEquals(Tensors.scalarMul(dummy, Factory.createScalarFromInitialValue(1.0)), Factory.createScalarFromInitialValue(1.0), "스칼라의 곱셈의 결과가 예상하던 것과 같다");

        });
    }
}
