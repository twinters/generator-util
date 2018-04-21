package be.thomaswinters.generator.selection;

public class Weighted<E> {
    private final E instance;
    private final double fitness;

    public Weighted(E instance, double fitness) {
        this.instance = instance;
        this.fitness = fitness;
    }


    public E getElement() {

        return instance;
    }

    public double getFitness() {
        return fitness;
    }
}

