package be.thomaswinters.generator.generators;

import be.thomaswinters.generator.stream.AMappingGenerator;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class MappingGenerator<E> extends AMappingGenerator<E, Supplier<Optional<E>>> {
    @SafeVarargs
    public MappingGenerator(Supplier<Optional<E>> innerGenerator, Function<E, E>... operators) {
        super(innerGenerator, operators);
    }
}
