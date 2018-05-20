package be.thomaswinters.generator.relatedgenerator;

import be.thomaswinters.generator.modifiers.AMaximisingGenerator;

import java.util.Comparator;
import java.util.Optional;

public class MaximisingRelatedGenerator<E> extends AMaximisingGenerator<E, IRelatedGenerator<E>> implements IRelatedGenerator<E> {

    public MaximisingRelatedGenerator(IRelatedGenerator<E> innerGenerator, int maxTrials, Comparator<E> comparator) {
        super(innerGenerator, maxTrials, comparator);
    }

    @Override
    public Optional<E> generateRelated(E input) {
        return maximise(() -> getInnerGenerator().generateRelated(input));
    }
}
