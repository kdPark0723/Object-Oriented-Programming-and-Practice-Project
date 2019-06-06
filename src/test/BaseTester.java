package test;

import java.util.HashMap;
import java.util.Map;

public class BaseTester {
    private int assertPassCounter;
    private int assertFailCounter;

    private int testPassCounter;
    private int testFailCounter;

    private Map<String, TestFunc> tests;

    private String name;

    BaseTester(String name) {
        this.name = name;
        tests = new HashMap<>();
    }

    void addTest(String name, TestFunc test) {
        tests.put(name, test);
    }

    void test() {
        testPassCounter = 0;
        testFailCounter = 0;

        startTests();

        for (Map.Entry<String, TestFunc> test : tests.entrySet()) {
            individualTest(test.getKey(), test.getValue());
        }

        finishTests();
    }

    private void startTests() {
        System.out.println(this.name + " [Total: " + tests.size() + "] is started.");
    }
    private void finishTests() {
        System.out.println(this.name + " [Pass: " + testPassCounter + ", Fail: " + testFailCounter + "] is finished.\n");
    }

    private void individualTest(String name, TestFunc test) {
        this.assertPassCounter = 0;
        this.assertFailCounter = 0;

        startTest(name);

        try {
            test.run();

            if (this.assertFailCounter > 0)
                failTest(name, "Assert: " + this.assertFailCounter + " is failed.");
            else
                passTest(name);
        } catch (Exception e) {
            failTest(name, e.getMessage());
        }
    }

    private void startTest(String name) {
        System.out.println("\t" + this.name + ": " + name + " is started.");
    }

    private void failTest(String name, String message) {
        testFailCounter++;
        System.out.println("\t" + this.name + ": " + name + "[" + message + "] is failed.\n");
    }

    private void passTest(String name) {
        testPassCounter++;
        System.out.println("\t" + this.name + ": " + name + " is passed.\n");
    }

    <T> void assertEquals(T lhs, T rhs, String testName) {
        try {
            boolean result = lhs.equals(rhs);
            if (result)
                passed(testName);
            else
                failed(testName, lhs.toString() + " is equal " + rhs.toString());
        } catch (Exception exception) {
            failed(testName, lhs.toString() + " is equal " + rhs.toString() + ", It throw Exception " + exception.getMessage());
        }
    }
    void assertTrue(boolean condition, String testName) {
        if (condition)
            passed(testName);
        else
            failed(testName, "");
    }

    private void passed(String testName) {
        assertPassCounter++;
        System.out.println("\t\t" + testName + " is passed.");
    }

    private void failed(String testName, String message) {
        assertFailCounter++;
        System.out.println("\t\t" + testName + "[" + message + "] is failed.");
    }
}
