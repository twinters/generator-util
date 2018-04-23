package be.thomaswinters.generator.generators;

import be.thomaswinters.generator.stream.ARetryingGenerator;

import java.util.Optional;
import java.util.function.Supplier;

public class RetryingGenerator<E> extends ARetryingGenerator<E, Supplier<Optional<E>>> implements IGenerator<E> {
    public RetryingGenerator(Supplier<Optional<E>> innerGenerator, int maxTrials) {
        super(innerGenerator, maxTrials);
    }

    public RetryingGenerator(Supplier<Optional<E>> innerGenerator) {
        super(innerGenerator);
    }
}
