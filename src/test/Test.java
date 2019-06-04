package test;

public class Test {
    public static void main(String[] args) {
        FactoryTest factoryTest = new FactoryTest();
        factoryTest.test();

        ScalarTest scalarTest = new ScalarTest();
        scalarTest.test();

        VectorTest vectorTest = new VectorTest();
        vectorTest.test();

        MatrixTest matrixTest = new MatrixTest();
        matrixTest.test();
    }
}
