package be.thomaswinters.generator.stream;

import be.thomaswinters.generator.generators.IGenerator;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class AMappingGenerator<E, F extends Supplier<Optional<E>>, G> implements IGenerator<G> {
    private final F innerGenerator;
    private final Function<E, G> operator;

    public AMappingGenerator(F innerGenerator, Function<E, G> operator) {
        this.innerGenerator = innerGenerator;
        this.operator = operator;
    }

    @Override
    public Optional<G> generate() {
        return innerGenerator.get().map(operator);
    }

    protected F getInnerGenerator() {
        return innerGenerator;
    }

    protected Function<E, G> getOperator() {
        return operator;
    }
}
