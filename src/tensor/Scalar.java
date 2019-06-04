package tensor;


public interface Scalar extends Comparable<Scalar>, Cloneable {
    void add(Scalar rhs);
    void sub(Scalar rhs);
    void mul(Scalar rhs);
    void div(Scalar rhs);

    Double get();
    void set(Double value);

    Object clone() throws CloneNotSupportedException;
}
