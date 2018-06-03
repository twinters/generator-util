package be.thomaswinters.generator.generators.related;

import be.thomaswinters.generator.generators.IGenerator;
import be.thomaswinters.generator.generators.reacting.IReactingGenerator;
import be.thomaswinters.generator.selection.ISelector;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;


public interface IRelatedGenerator<E,F> extends IGenerator<E>, IReactingGenerator<E,F> {

    default RelatedGenerator<E,F> updateGenerator(Function<IGenerator<E>, ? extends Supplier<Optional<E>>> mapper) {
        return new RelatedGenerator<>(mapper.apply(this), this::generateRelated);
    }

    default RelatedGenerator<E,F> updateRelatedGenerator(Function<IRelatedGenerator<E,F>, ? extends IRelatedGenerator<E,F>> mapper) {
        return new RelatedGenerator<>(this::generate, mapper.apply(this));
    }

    @Override
    default <G> IRelatedGenerator<G,F> map(Function<E, G> operator) {
        return new MappingRelatedGenerator<>(this, operator);
    }

    @Override
    default IRelatedGenerator<E,F> peek(Consumer<E> consumer) {
        return map(e-> {
            consumer.accept(e);
            return e;
        });
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
