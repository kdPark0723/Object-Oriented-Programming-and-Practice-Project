package tensor;

public class Tensors {
    public static Scalar scalarClone(Scalar origin) throws CloneNotSupportedException {
        return (Scalar)(origin.clone());
    }

    public static Vector vectorClone(Vector origin) throws CloneNotSupportedException {
        return (Vector)(origin.clone());
    }

    public static Scalar scalarAdd(Scalar lhs, Scalar rhs) {
        return Scalar.add(lhs, rhs);
    }

    public static Scalar scalarMul(Scalar lhs, Scalar rhs) {
        return Scalar.mul(lhs, rhs);
    }

    public static Vector vectorAdd(Vector lhs, Vector rhs) {
        return Vector.add(lhs, rhs);
    }

    public static Vector vectorMul(Vector lhs, Scalar rhs) {
        return Vector.mul(lhs, rhs);
    }
}
