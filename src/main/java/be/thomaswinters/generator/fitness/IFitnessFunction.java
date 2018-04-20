package be.thomaswinters.generator.fitness;

import java.util.function.Function;

@FunctionalInterface
public interface IFitnessFunction<E> extends Function<E, Double> {
    double getFitness(E input);

    @Override
    default Double apply(E e) {
        return getFitness(e);
    }
}
