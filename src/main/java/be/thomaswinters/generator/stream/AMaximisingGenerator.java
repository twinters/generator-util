package be.thomaswinters.generator.stream;

import be.thomaswinters.generator.generators.IGenerator;
import be.thomaswinters.generator.generators.RetryingGenerator;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class AMaximisingGenerator<E, F extends Supplier<Optional<E>>> extends RetryingGenerator<E, F> implements IGenerator<E> {
    private Comparator<E> comparator;

    public AMaximisingGenerator(F innerGenerator, int maxTrials, Comparator<E> comparator) {
        super(innerGenerator, maxTrials);
        this.comparator = comparator;
    }


    @Override
    public Optional<E> generate() {
        return maximise(getInnerGenerator());

    }

    protected Optional<E> maximise(Supplier<Optional<E>> generator) {
        return Stream.generate(generator)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .max(comparator);
    }
}
