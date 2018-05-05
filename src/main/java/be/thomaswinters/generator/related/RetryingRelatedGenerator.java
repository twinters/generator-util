package be.thomaswinters.generator.related;

import be.thomaswinters.generator.stream.ARetryingGenerator;

import java.util.Optional;

public class RetryingRelatedGenerator<E> extends ARetryingGenerator<E, IRelatedGenerator<E>> implements IRelatedGenerator<E> {
    public RetryingRelatedGenerator(IRelatedGenerator<E> innerGenerator, int maxTrials) {
        super(innerGenerator, maxTrials);
    }

    public RetryingRelatedGenerator(IRelatedGenerator<E> innerGenerator) {
        super(innerGenerator);
    }

    @Override
    public Optional<E> generateRelated(E input) {
        return createRetryingStream(() -> getInnerGenerator().generateRelated(input)).findFirst();
    }
}

