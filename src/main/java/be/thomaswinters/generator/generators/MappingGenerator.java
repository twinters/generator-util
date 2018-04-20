package be.thomaswinters.generator.generators;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class MappingGenerator<E> implements IGenerator<E> {
    private final Supplier<Optional<E>> innerGenerator;
    private final UnaryOperator<E> operator;

    public MappingGenerator(Supplier<Optional<E>> innerGenerator, UnaryOperator<E> operator) {
        this.innerGenerator = innerGenerator;
        this.operator = operator;
    }

    @Override
    public Optional<E> generate() {
        return innerGenerator.get().map(operator);
    }
}
