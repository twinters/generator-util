package be.thomaswinters.generator.stream;

import be.thomaswinters.generator.generators.IGenerator;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

public abstract class AGeneratorDecorator<E, F extends Supplier<Optional<E>>> implements IGenerator<E> {
    private final F innerGenerator;

    public AGeneratorDecorator(F innerGenerator) {
        this.innerGenerator = innerGenerator;
    }


    protected Stream<Optional<E>> generateStream() {
        return Stream.generate(getInnerGenerator());
    }

    protected F getInnerGenerator() {
        return innerGenerator;
    }
}
