package tensor;


public interface Scalar extends Comparable<Scalar>, Cloneable {
    Double get();

    void set(Double value);

    static Scalar add(Scalar lhs, Scalar rhs) {
            try {
                Scalar result = (Scalar)lhs.clone();
                result.add(rhs);
                return result;
            } catch (CloneNotSupportedException e) {
                return null;
        }
    }

    void add(Scalar rhs);

    static Scalar sub(Scalar lhs, Scalar rhs) {
        try {
            Scalar result = (Scalar)lhs.clone();
            result.sub(rhs);
            return result;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    void sub(Scalar rhs);

    static Scalar mul(Scalar lhs, Scalar rhs) {
        try {
            Scalar result = (Scalar)lhs.clone();
            result.mul(rhs);
            return result;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    void mul(Scalar rhs);

    static Scalar div(Scalar lhs, Scalar rhs) {
        try {
            Scalar result = (Scalar)lhs.clone();
            result.div(rhs);
            return result;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    void div(Scalar rhs);

    Object clone() throws CloneNotSupportedException;
}
