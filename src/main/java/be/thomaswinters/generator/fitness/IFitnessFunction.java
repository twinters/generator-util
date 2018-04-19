package be.thomaswinters.generator.fitness;

public interface IFitnessFunction<E> {
    double getFitness(E input);
}
