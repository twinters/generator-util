package be.thomaswinters.generator.stream;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;


public abstract class ARetryingGenerator<E, F extends Supplier<Optional<E>>> extends AGeneratorDecorator<E, F> {
    private final int maxTrials;

    //region Constructor
    public ARetryingGenerator(F innerGenerator, int maxTrials) {
        super(innerGenerator);
        this.maxTrials = maxTrials;
    }

    public ARetryingGenerator(F innerGenerator) {
        this(innerGenerator, Integer.MAX_VALUE);
    }
    //endregion

    @Override
    public Optional<E> generate() {
        return createRetryingStream(getInnerGenerator())
                .findFirst();
    }

    protected Stream<E> createRetryingStream(Supplier<Optional<E>> generator) {
        return Stream.generate(generator)
                .limit(maxTrials)
                .filter(Optional::isPresent)
                .map(Optional::get);
    }


}
