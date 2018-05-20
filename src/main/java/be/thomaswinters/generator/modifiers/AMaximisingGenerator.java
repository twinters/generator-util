package be.thomaswinters.generator.modifiers;

import be.thomaswinters.generator.generators.IGenerator;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Supplier;

public class AMaximisingGenerator<E, F extends Supplier<Optional<E>>> extends ARetryingGenerator<E, F> implements IGenerator<E> {
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
        return createRetryingStream(generator)
                .max(comparator);
    }
}
