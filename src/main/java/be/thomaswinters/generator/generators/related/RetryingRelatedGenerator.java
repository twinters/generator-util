package be.thomaswinters.generator.generators.related;

import be.thomaswinters.generator.modifiers.ARetryingGenerator;

import java.util.Optional;

public class RetryingRelatedGenerator<E,F> extends ARetryingGenerator<E, IRelatedGenerator<E,F>> implements IRelatedGenerator<E,F> {
    public RetryingRelatedGenerator(IRelatedGenerator<E,F> innerGenerator, int maxTrials) {
        super(innerGenerator, maxTrials);
    }

    public RetryingRelatedGenerator(IRelatedGenerator<E,F> innerGenerator) {
        super(innerGenerator);
    }

    @Override
    public Optional<E> generateRelated(F input) {
        return createRetryingStream(() -> getInnerGenerator().generateRelated(input)).findFirst();
    }
}

