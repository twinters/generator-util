package be.thomaswinters.generator.related;

import be.thomaswinters.generator.stream.AFilteringGenerator;

import java.util.Optional;
import java.util.function.Predicate;

public class FilteringRelatedGenerator<E> extends AFilteringGenerator<E, IRelatedGenerator<E>> implements IRelatedGenerator<E> {

    @SafeVarargs
    public FilteringRelatedGenerator(IRelatedGenerator<E> innerGenerator, int maxTrials, Predicate<E>... filters) {
        super(innerGenerator, maxTrials, filters);
    }

    @SafeVarargs
    public FilteringRelatedGenerator(IRelatedGenerator<E> innerGenerator, Predicate<E>... filters) {
        super(innerGenerator, filters);
    }

    @Override
    public Optional<E> generateRelated(E input) {
        return filter(() -> getInnerGenerator().generateRelated(input));
    }
}
