package test;

import java.io.FileNotFoundException;

@FunctionalInterface
interface TestFunc {
    void run() throws FileNotFoundException, CloneNotSupportedException;
}
