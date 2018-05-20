package be.thomaswinters.generator.relatedgenerator;

import be.thomaswinters.generator.generators.IGenerator;
import be.thomaswinters.generator.selection.ISelector;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;


public interface IRelatedGenerator<E,F> extends IGenerator<E>, Function<F, Optional<E>> {
    Optional<E> generateRelated(F input);

    @Override
    default Optional<E> apply(F e) {
        return generateRelated(e);
    }


    default RelatedGenerator<E,F> updateGenerator(Function<IGenerator<E>, ? extends Supplier<Optional<E>>> mapper) {
        return new RelatedGenerator<>(mapper.apply(this), this::generateRelated);
    }

    default RelatedGenerator<E,F> updateRelatedGenerator(Function<IRelatedGenerator<E,F>, ? extends IRelatedGenerator<E,F>> mapper) {
        return new RelatedGenerator<>(this::generate, mapper.apply(this));
    }

    @Override
    default IRelatedGenerator<E,F> map(Function<E, E> operator) {
        return new MappingRelatedGenerator<>(this, operator);
    }

    @Override
    default <G> IRelatedGenerator<G,F> mapToDifferent(Function<E, G> operator) {
        throw new UnsupportedOperationException("Related Generators don't support mapping to a different object");
    }

    @Override
    default IRelatedGenerator<E,F> max(int maxTrials, Comparator<E> comparator) {
        return new MaximisingRelatedGenerator<>(this, maxTrials, comparator);
    }

    @Override
    default IRelatedGenerator<E,F> retry(int maxTrials) {
        return new RetryingRelatedGenerator<>(this, maxTrials);
    }

    @Override
    default IRelatedGenerator<E,F> retry() {
        return new RetryingRelatedGenerator<>(this);
    }

    @Override
    default IRelatedGenerator<E,F> select(int amountOfGenerations, ISelector<E> selector) {
        return new SelectionRelatedGenerator<>(this, amountOfGenerations, selector);
    }

    @Override
    default IRelatedGenerator<E,F> filter(int maxTrials, Predicate<E> filter) {
        return new FilteringRelatedGenerator<>(this, maxTrials, filter);
    }

    @Override
    default IRelatedGenerator<E,F> filter(Predicate<E> filter) {
        return new FilteringRelatedGenerator<>(this, filter);
    }
}