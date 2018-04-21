package be.thomaswinters.generator.related;

import be.thomaswinters.generator.generators.IGenerator;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;


public interface IRelatedGenerator<E> extends IGenerator<E> {
    default Optional<E> generateRelated(Optional<E> input) {
        return input.map(this::generateRelated).orElse(generate());
    }

    Optional<E> generateRelated(E input);


    default RelatedGenerator<E> updateGenerator(Function<Supplier<Optional<E>>, ? extends Supplier<Optional<E>>> mapper) {
        return new RelatedGenerator<E>(mapper.apply(this::generate), this::generateRelated);
    }

    default RelatedGenerator<E> updateRelatedGenerator(Function<Function<E, Optional<E>>, ? extends Function<E, Optional<E>>> mapper) {
        return new RelatedGenerator<>(this::generate, mapper.apply(this::generateRelated));
    }
}
