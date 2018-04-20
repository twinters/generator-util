package be.thomaswinters.generator.generators;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class MappingGenerator<E> implements IGenerator<E> {
    private final Supplier<Optional<E>> innerGenerator;
    private final Function<E, E> operator;

    public MappingGenerator(Supplier<Optional<E>> innerGenerator, Function<E, E>... operators) {
        this.innerGenerator = innerGenerator;
        this.operator = Stream.of(operators).reduce(Function.identity(), Function::andThen);
    }


    @Override
    public Optional<E> generate() {
        return innerGenerator.get().map(operator);
    }
}
