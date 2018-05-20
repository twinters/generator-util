package be.thomaswinters.generator.generators;

import be.thomaswinters.generator.modifiers.AMappingGenerator;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class DifferentMappingGenerator<E, F extends Supplier<Optional<E>>, G> extends AMappingGenerator<E, F, G> {

    public DifferentMappingGenerator(F innerGenerator, Function<E, G> operator) {
        super(innerGenerator, operator);
    }
}
