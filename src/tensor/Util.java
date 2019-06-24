package tensor;

class Util {
    static Double getRandomValue(Double minValue, Double maxValue) {
        Double randomValue;

        do {
            randomValue = Math.random();
            randomValue *= maxValue - minValue;
            randomValue += minValue;
        } while (randomValue.equals(maxValue));

        return randomValue;
    }
}
