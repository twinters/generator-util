package be.thomaswinters.generator.generators;

import be.thomaswinters.generator.modifiers.AMaximisingGenerator;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Supplier;

public class MaximisingGenerator<E> extends AMaximisingGenerator<E, Supplier<Optional<E>>> implements IGenerator<E> {

    public MaximisingGenerator(Supplier<Optional<E>> innerGenerator, int maxTrials, Comparator<E> comparator) {
        super(innerGenerator, maxTrials, comparator);
    }

}
