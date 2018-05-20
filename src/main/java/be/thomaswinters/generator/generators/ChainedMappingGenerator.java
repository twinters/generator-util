package be.thomaswinters.generator.generators;

import be.thomaswinters.generator.modifiers.AMappingGenerator;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class ChainedMappingGenerator<E> extends AMappingGenerator<E, Supplier<Optional<E>>, E> {

    @SafeVarargs
    public ChainedMappingGenerator(Supplier<Optional<E>> innerGenerator, Function<E, E>... operator) {
        super(innerGenerator, Stream.of(operator).reduce(Function.identity(), Function::andThen));
    }

}
