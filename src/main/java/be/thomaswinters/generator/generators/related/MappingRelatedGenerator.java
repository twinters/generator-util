package be.thomaswinters.generator.generators.related;

import be.thomaswinters.generator.modifiers.AMappingGenerator;

import java.util.Optional;
import java.util.function.Function;

public class MappingRelatedGenerator<E,F,G> extends AMappingGenerator<E, IRelatedGenerator<E,F>, G> implements IRelatedGenerator<G,F> {

    public MappingRelatedGenerator(IRelatedGenerator<E,F> innerGenerator, Function<E, G> operator) {
        super(innerGenerator, operator);
    }

    @Override
    public Optional<G> generateRelated(F input) {
        return getInnerGenerator().generateRelated(input).map(getOperator());
    }
}
