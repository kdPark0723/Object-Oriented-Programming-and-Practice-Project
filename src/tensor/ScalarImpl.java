package tensor;

class ScalarImpl implements Scalar {
    private Double value;

    ScalarImpl(Double initValue) {
        value = initValue;
    }

    ScalarImpl(Double minValue, Double maxValue) {
        value = Util.getRandomValue(minValue, maxValue);
    }

    static Scalar add(Scalar lhs, Scalar rhs) {
        Scalar result = lhs.clone();
        result.add(rhs);
        return result;
    }

    static Scalar sub(Scalar lhs, Scalar rhs) {
        Scalar result = lhs.clone();
        result.sub(rhs);

        return result;
    }

    static Scalar mul(Scalar lhs, Scalar rhs) {
        Scalar result = lhs.clone();
        result.mul(rhs);

        return result;
    }

    static Scalar div(Scalar lhs, Scalar rhs) {
        Scalar result = lhs.clone();
        result.div(rhs);

        return result;
    }

    @Override
    public void add(Scalar rhs) {
        value += rhs.get();
    }

    @Override
    public void sub(Scalar rhs) {
        value -= rhs.get();
    }

    @Override
    public void mul(Scalar rhs) {
        value *= rhs.get();
    }

    @Override
    public void div(Scalar rhs) {
        value /= rhs.get();
    }

    @Override
    public Double get() {
        return value;
    }

    @Override
    public void set(Double value) {
        this.value = value;
    }

    @Override
    public Scalar clone() {
        try {
            ScalarImpl cloned = (ScalarImpl) super.clone();
            cloned.value = new Double(this.value.doubleValue());

            return cloned;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object obj) {
        double error;
        error = ((Scalar) obj).get() - value;

        return error < 1.0e-10 && error > -1.0e-10;
    }

    @Override
    public int compareTo(Scalar o) {
        return value.compareTo(o.get());
    }
}
