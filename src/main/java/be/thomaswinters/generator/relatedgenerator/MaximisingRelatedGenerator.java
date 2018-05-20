package be.thomaswinters.generator.relatedgenerator;

import be.thomaswinters.generator.modifiers.AMaximisingGenerator;

import java.util.Comparator;
import java.util.Optional;

public class MaximisingRelatedGenerator<E,F> extends AMaximisingGenerator<E, IRelatedGenerator<E,F>> implements IRelatedGenerator<E,F> {

    public MaximisingRelatedGenerator(IRelatedGenerator<E,F> innerGenerator, int maxTrials, Comparator<E> comparator) {
        super(innerGenerator, maxTrials, comparator);
    }

    @Override
    public Optional<E> generateRelated(F input) {
        return maximise(() -> getInnerGenerator().generateRelated(input));
    }
}
