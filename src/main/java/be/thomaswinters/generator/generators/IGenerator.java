package be.thomaswinters.generator.generators;

import java.util.Optional;
import java.util.function.Supplier;

public interface IGenerator<E> extends Supplier<Optional<E>> {
    Optional<E> generate();

    @Override
    default Optional<E> get() {
        return generate();
    }
}
