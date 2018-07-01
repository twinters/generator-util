package be.thomaswinters.numbergenerator.generators;

import java.util.List;
import java.util.stream.Stream;

public class SummationNumberGenerator extends ANumberGeneratorComposite {
    public SummationNumberGenerator(List<INumberGenerator> generators) {
        super(generators);
    }

    @Override
    protected int apply(Stream<Integer> generated) {
        return generated.mapToInt(e -> e).sum();
    }
}
