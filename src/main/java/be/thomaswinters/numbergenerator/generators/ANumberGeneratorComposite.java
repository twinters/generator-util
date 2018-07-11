package be.thomaswinters.numbergenerator.generators;

import java.util.List;
import java.util.stream.Stream;

public abstract class ANumberGeneratorComposite implements INumberGenerator {
    private final List<INumberGenerator> generators;

    public ANumberGeneratorComposite(List<INumberGenerator> generators) {
        this.generators = generators;
    }

    @Override
    public int generateNumber() {
        return apply(generators.stream().map(INumberGenerator::generateNumber));
    }

    protected abstract int apply(Stream<Integer> generated);
}
