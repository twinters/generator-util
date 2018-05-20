package be.thomaswinters.generator.relatedgenerator;

import be.thomaswinters.generator.modifiers.AMappingGenerator;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class MappingRelatedGenerator<E,F> extends AMappingGenerator<E, IRelatedGenerator<E,F>, E> implements IRelatedGenerator<E,F> {

    @SafeVarargs
    public MappingRelatedGenerator(IRelatedGenerator<E,F> innerGenerator, Function<E, E>... operator) {
        super(innerGenerator, Stream.of(operator).reduce(Function.identity(), Function::andThen));
    }

    @Override
    public Optional<E> generateRelated(F input) {
        return getInnerGenerator().generateRelated(input).map(getOperator());
    }
}
