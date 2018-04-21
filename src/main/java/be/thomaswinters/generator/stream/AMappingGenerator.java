package be.thomaswinters.generator.stream;

import be.thomaswinters.generator.generators.IGenerator;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public abstract class AMappingGenerator<E, F extends Supplier<Optional<E>>> implements IGenerator<E> {
    private final F innerGenerator;
    private final Function<E, E> operator;

    @SafeVarargs
    public AMappingGenerator(F innerGenerator, Function<E, E>... operators) {
        this.innerGenerator = innerGenerator;
        this.operator = Stream.of(operators).reduce(Function.identity(), Function::andThen);
    }

    @Override
    public Optional<E> generate() {
        return innerGenerator.get().map(operator);
    }

    protected F getInnerGenerator() {
        return innerGenerator;
    }

    protected Function<E, E> getOperator() {
        return operator;
    }
}
