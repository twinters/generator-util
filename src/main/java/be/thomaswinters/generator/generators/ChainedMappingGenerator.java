package be.thomaswinters.generator.generators;

import be.thomaswinters.generator.stream.AMappingGenerator;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class ChainedMappingGenerator<E, F extends Supplier<Optional<E>>> extends AMappingGenerator<E, F, E> {

    public ChainedMappingGenerator(F innerGenerator, Function<E, E>... operator) {
        super(innerGenerator, Stream.of(operator).reduce(Function.identity(), Function::andThen));
    }
}
