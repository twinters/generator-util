package be.thomaswinters.generator.generators;

import be.thomaswinters.generator.selection.ISelector;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@FunctionalInterface
public interface IGenerator<E> extends Supplier<Optional<E>> {
    Optional<E> generate();

    @Override
    default Optional<E> get() {
        return generate();
    }

    default IGenerator<E> map(Function<E, E> operator) {
        return new ChainedMappingGenerator<>(this, operator);
    }

    default <G> IGenerator<G> mapToDifferent(Function<E, G> operator) {
        return new MappingGenerator<E, IGenerator<E>, G>(this, operator);
    }

    default IGenerator<E> max(int maxTrials, Comparator<E> comparator) {
        return new MaximisingGenerator<>(this, maxTrials, comparator);
    }

    default IGenerator<E> retry(int maxTrials) {
        return new RetryingGenerator<>(this, maxTrials);
    }

    default IGenerator<E> retry() {
        return new RetryingGenerator<>(this);
    }

    default IGenerator<E> select(int amountOfGenerations, ISelector<E> selector) {
        return new SelectionGenerator<>(this, amountOfGenerations, selector);
    }

    default IGenerator<E> filter(int maxTrials, Predicate<E>... filters) {
        return new FilteringGenerator<>(this, maxTrials, filters);
    }

    default IGenerator<E> filter(Predicate<E> filter) {
        return new FilteringGenerator<>(this, filter);
    }

}
