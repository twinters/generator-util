package be.thomaswinters.generator.generators;

import java.util.Optional;
import java.util.function.Supplier;

public class Generator<E> implements IGenerator<E> {
    private Supplier<Optional<E>> generator;

    public Generator(Supplier<Optional<E>> generator) {
        this.generator = generator;
    }

    @Override
    public Optional<E> generate() {
        return generator.get();
    }
}
