package test;

import java.util.ArrayList;

public class BaseTest {
    ArrayList<BaseTester> tests;

    BaseTest() {
        tests = new ArrayList<>();
    }

    BaseTester addTest(String name) {
        BaseTester test = new BaseTester(name);
        tests.add(test);

        return test;
    }

    void test() {
        for (BaseTester test : tests)
            test.test();
    }
}
