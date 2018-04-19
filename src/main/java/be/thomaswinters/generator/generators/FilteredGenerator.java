package be.thomaswinters.generator.generators;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class FilteredGenerator<E> implements IGenerator<E> {
    private final int maxTrials;
    private final IGenerator<E> innerGenerator;
    private final Predicate<E> filter;

    public FilteredGenerator(IGenerator<E> innerGenerator, Predicate<E> filter, int maxTrials) {
        this.innerGenerator = innerGenerator;
        this.filter = filter;
        this.maxTrials = maxTrials;
    }

    public FilteredGenerator(IGenerator innerGenerator, Predicate<E> filter) {
        this(innerGenerator, filter, Integer.MAX_VALUE);
    }

    @Override
    public Optional<E> generate() {
        return Stream.generate(innerGenerator::generate)
                .limit(maxTrials)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(filter)
                .findFirst();
    }
}
