package be.thomaswinters.generator.generators.related;

import be.thomaswinters.generator.modifiers.AFilteringGenerator;

import java.util.Optional;
import java.util.function.Predicate;

public class FilteringRelatedGenerator<E,F> extends AFilteringGenerator<E, IRelatedGenerator<E,F>> implements IRelatedGenerator<E,F> {

    @SafeVarargs
    public FilteringRelatedGenerator(IRelatedGenerator<E,F> innerGenerator, int maxTrials, Predicate<E>... filters) {
        super(innerGenerator, maxTrials, filters);
    }

    @SafeVarargs
    public FilteringRelatedGenerator(IRelatedGenerator<E,F> innerGenerator, Predicate<E>... filters) {
        super(innerGenerator, filters);
    }

    @Override
    public Optional<E> generateRelated(F input) {
        return filter(() -> getInnerGenerator().generateRelated(input));
    }
}
