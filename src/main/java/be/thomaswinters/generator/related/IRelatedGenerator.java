package be.thomaswinters.generator.related;

import be.thomaswinters.generator.generators.IGenerator;

import java.util.Optional;


public interface IRelatedGenerator<E> extends IGenerator<E> {
    default Optional<E> generateRelated(Optional<E> input) {
        return input.map(this::generateRelated).orElse(generate());
    }

    Optional<E> generateRelated(E input);
}
