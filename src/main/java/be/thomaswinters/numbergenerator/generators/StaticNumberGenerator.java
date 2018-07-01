package be.thomaswinters.numbergenerator.generators;

public class StaticNumberGenerator implements INumberGenerator {
    private final int value;

    public StaticNumberGenerator(int value) {
        this.value = value;
    }

    @Override
    public int generateNumber() {
        return value;
    }
}
