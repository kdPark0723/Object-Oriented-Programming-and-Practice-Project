package tensor;


public interface Scalar extends Comparable<Scalar>, Cloneable {
    Double get();

    void set(Double value);

    void add(Scalar rhs);

    void sub(Scalar rhs);

    void mul(Scalar rhs);

    void div(Scalar rhs);

    Object clone() throws CloneNotSupportedException;
}
