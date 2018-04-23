package be.thomaswinters.generator.stream;

import be.thomaswinters.generator.generators.IGenerator;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;


public abstract class ARetryingGenerator<E, F extends Supplier<Optional<E>>> implements IGenerator<E> {
    private final int maxTrials;
    private final F innerGenerator;

    //region Constructor
    public ARetryingGenerator(F innerGenerator, int maxTrials) {
        this.innerGenerator = innerGenerator;
        this.maxTrials = maxTrials;
    }

    public ARetryingGenerator(F innerGenerator) {
        this(innerGenerator, Integer.MAX_VALUE);
    }
    //endregion

    @Override
    public Optional<E> generate() {
        return createRetryingStream(innerGenerator)
                .findFirst();
    }

    protected Stream<E> createRetryingStream(Supplier<Optional<E>> generator) {
        return Stream.generate(generator)
                .limit(maxTrials)
                .filter(Optional::isPresent)
                .map(Optional::get);
    }


    protected F getInnerGenerator() {
        return innerGenerator;
    }

}
