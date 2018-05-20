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

    default <G> IGenerator<G> map(Function<E, G> operator) {
        return new DifferentMappingGenerator<>(this, operator);
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

    default IGenerator<E> filter(int maxTrials, Predicate<E> filter) {
        return new FilteringGenerator<>(this, maxTrials, filter);
    }

    default IGenerator<E> filter(Predicate<E> filter) {
        return new FilteringGenerator<>(this, filter);
    }

}
