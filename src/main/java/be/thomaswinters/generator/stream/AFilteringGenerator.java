package be.thomaswinters.generator.stream;

import be.thomaswinters.generator.generators.IGenerator;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public abstract class AFilteringGenerator<E, F extends Supplier<Optional<E>>> implements IGenerator<E> {
    private final int maxTrials;
    private final F innerGenerator;
    private final Predicate<E> filter;

    //region Constructor
    public AFilteringGenerator(F innerGenerator, int maxTrials, Predicate<E>... filters) {
        this.innerGenerator = innerGenerator;
        this.filter = Stream.of(filters).reduce(x -> true, Predicate::and);
        this.maxTrials = maxTrials;
    }

    public AFilteringGenerator(F innerGenerator, Predicate<E>... filters) {
        this(innerGenerator, Integer.MAX_VALUE, filters);
    }
    //endregion

    @Override
    public Optional<E> generate() {
        return filter(innerGenerator);

    }

    protected Optional<E> filter(Supplier<Optional<E>> generator) {
        return Stream.generate(generator)
                .limit(maxTrials)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(filter)
                .findFirst();
    }

    protected F getInnerGenerator() {
        return innerGenerator;
    }

}
