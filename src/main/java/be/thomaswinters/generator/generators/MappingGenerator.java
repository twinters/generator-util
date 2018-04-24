package be.thomaswinters.generator.generators;

import be.thomaswinters.generator.stream.AMappingGenerator;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class MappingGenerator<E, F extends Supplier<Optional<E>>, G> extends AMappingGenerator<E, F, G> {

    public MappingGenerator(F innerGenerator, Function<E, G> operator) {
        super(innerGenerator, operator);
    }
}
