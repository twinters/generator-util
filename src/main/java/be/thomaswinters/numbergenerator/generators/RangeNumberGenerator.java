package be.thomaswinters.numbergenerator.generators;

import java.util.concurrent.ThreadLocalRandom;

public class RangeNumberGenerator implements INumberGenerator {
    private final INumberGenerator startInclusive;
    private final INumberGenerator endInclusive;

    public RangeNumberGenerator(INumberGenerator startInclusive, INumberGenerator endInclusive) {
        this.startInclusive = startInclusive;
        this.endInclusive = endInclusive;
    }

    public RangeNumberGenerator(int startInclusive, int endInclusive) {
        this(new StaticNumberGenerator(startInclusive), new StaticNumberGenerator(endInclusive));
    }

    public static RangeNumberGenerator createDice(int eyes) {
        return new RangeNumberGenerator(1, eyes);
    }


    @Override
    public int generateNumber() {
        int start = startInclusive.generateNumber();
        int end = endInclusive.generateNumber();
        return ThreadLocalRandom.current().nextInt(end + 1 - start) + start;
    }
}
