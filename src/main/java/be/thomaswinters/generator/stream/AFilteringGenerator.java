package be.thomaswinters.generator.stream;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public abstract class AFilteringGenerator<E, F extends Supplier<Optional<E>>> extends ARetryingGenerator<E, F> {
    private final Predicate<E> filter;

    //region Constructor
    @SafeVarargs
    public AFilteringGenerator(F innerGenerator, int maxTrials, Predicate<E>... filters) {
        super(innerGenerator, maxTrials);
        this.filter = Stream.of(filters).reduce(x -> true, Predicate::and);
    }

    @SafeVarargs
    public AFilteringGenerator(F innerGenerator, Predicate<E>... filters) {
        this(innerGenerator, Integer.MAX_VALUE, filters);
    }
//endregion

    @Override
    public Optional<E> generate() {
        return filter(getInnerGenerator());

    }

    protected Optional<E> filter(Supplier<Optional<E>> generator) {
        return createRetryingStream(generator)
                .filter(filter)
                .findFirst();
    }

}
