package be.thomaswinters.generator.generators;

import java.util.Optional;

public interface IGenerator<E> {
    Optional<E> generate();
}
