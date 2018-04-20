package be.thomaswinters.generator.generators;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class FilteringGenerator<E> implements IGenerator<E> {
    private final int maxTrials;
    private final IGenerator<E> innerGenerator;
    private final Predicate<E> filter;

    public FilteringGenerator(IGenerator<E> innerGenerator, int maxTrials, Predicate<E>... filters) {
        this.innerGenerator = innerGenerator;
        this.filter = Stream.of(filters).reduce(x -> true, Predicate::and);
        this.maxTrials = maxTrials;
    }

    public FilteringGenerator(IGenerator innerGenerator, Predicate<E>... filters) {
        this(innerGenerator, Integer.MAX_VALUE, filters);
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
