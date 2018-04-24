package be.thomaswinters.generator.generators;

import be.thomaswinters.generator.stream.ARetryingGenerator;

import java.util.Optional;
import java.util.function.Supplier;

public class RetryingGenerator<E, F extends Supplier<Optional<E>>> extends ARetryingGenerator<E, F> implements IGenerator<E> {
    public RetryingGenerator(F innerGenerator, int maxTrials) {
        super(innerGenerator, maxTrials);
    }

    public RetryingGenerator(F innerGenerator) {
        super(innerGenerator);
    }
}
