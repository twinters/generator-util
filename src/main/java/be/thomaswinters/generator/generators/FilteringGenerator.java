package be.thomaswinters.generator.generators;

import be.thomaswinters.generator.stream.AFilteringGenerator;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FilteringGenerator<E> extends AFilteringGenerator<E, Supplier<Optional<E>>> implements IGenerator<E> {
    @SafeVarargs
    public FilteringGenerator(Supplier<Optional<E>> innerGenerator, int maxTrials, Predicate<E>... filters) {
        super(innerGenerator, maxTrials, filters);
    }

    @SafeVarargs
    public FilteringGenerator(Supplier<Optional<E>> innerGenerator, Predicate<E>... filters) {
        super(innerGenerator, filters);
    }
}
