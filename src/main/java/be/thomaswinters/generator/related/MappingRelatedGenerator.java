package be.thomaswinters.generator.related;

import be.thomaswinters.generator.stream.AMappingGenerator;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class MappingRelatedGenerator<E> extends AMappingGenerator<E, IRelatedGenerator<E>, E> implements IRelatedGenerator<E> {

    @SafeVarargs
    public MappingRelatedGenerator(IRelatedGenerator<E> innerGenerator, Function<E, E>... operator) {
        super(innerGenerator, Stream.of(operator).reduce(Function.identity(), Function::andThen));
    }

    @Override
    public Optional<E> generateRelated(E input) {
        return getInnerGenerator().generateRelated(input).map(getOperator());
    }
}
