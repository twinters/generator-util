package be.thomaswinters.generator.related;

import be.thomaswinters.generator.stream.AMappingGenerator;

import java.util.Optional;
import java.util.function.Function;

public class MappingRelatedGenerator<E> extends AMappingGenerator<E, IRelatedGenerator<E>, E> implements IRelatedGenerator<E> {

    public MappingRelatedGenerator(IRelatedGenerator<E> innerGenerator, Function<E, E> operator) {
        super(innerGenerator, operator);
    }

    @Override
    public Optional<E> generateRelated(E input) {
        return getInnerGenerator().generateRelated(input).map(getOperator());
    }
}
