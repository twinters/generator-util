package be.thomaswinters.numbergenerator.generators;

import java.util.List;
import java.util.stream.Stream;

public class MultiplicationNumberGenerator extends ANumberGeneratorComposite {
    public MultiplicationNumberGenerator(List<INumberGenerator> generators) {
        super(generators);
    }

    @Override
    protected int apply(Stream<Integer> generated) {
        return generated.reduce(1, (e, f) -> e * f);
    }
}
