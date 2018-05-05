package be.thomaswinters.generator.related;

import be.thomaswinters.generator.generators.IGenerator;
import be.thomaswinters.generator.selection.ISelector;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;


public interface IRelatedGenerator<E> extends IGenerator<E>, Function<E, Optional<E>> {
    Optional<E> generateRelated(E input);

    @Override
    default Optional<E> apply(E e) {
        return generateRelated(e);
    }


    default RelatedGenerator<E> updateGenerator(Function<IGenerator<E>, ? extends Supplier<Optional<E>>> mapper) {
        return new RelatedGenerator<E>(mapper.apply(this), this::generateRelated);
    }

    default RelatedGenerator<E> updateRelatedGenerator(Function<IRelatedGenerator<E>, ? extends IRelatedGenerator<E>> mapper) {
        return new RelatedGenerator<>(this::generate, mapper.apply(this));
    }

    @Override
    default IRelatedGenerator<E> map(Function<E, E> operator) {
        return new MappingRelatedGenerator<>(this, operator);
    }

    @Override
    default <G> IRelatedGenerator<G> mapToDifferent(Function<E, G> operator) {
        throw new UnsupportedOperationException("Related Generators don't support mapping to a different object");
    }

    @Override
    default IRelatedGenerator<E> max(int maxTrials, Comparator<E> comparator) {
        return new MaximisingRelatedGenerator<>(this, maxTrials, comparator);
    }

    @Override
    default IRelatedGenerator<E> retry(int maxTrials) {
        return new RetryingRelatedGenerator<>(this, maxTrials);
    }

    @Override
    default IRelatedGenerator<E> retry() {
        return new RetryingRelatedGenerator<>(this);
    }

    @Override
    default IRelatedGenerator<E> select(int amountOfGenerations, ISelector<E> selector) {
        return new SelectionRelatedGenerator<>(this, amountOfGenerations, selector);
    }

    @Override
    default IRelatedGenerator<E> filter(int maxTrials, Predicate<E> filter) {
        return new FilteringRelatedGenerator<>(this, maxTrials, filter);
    }

    @Override
    default IRelatedGenerator<E> filter(Predicate<E> filter) {
        return new FilteringRelatedGenerator<>(this, filter);
    }
}
