package be.thomaswinters.generator.selection;

import java.util.function.Function;

public class Weighted<E> {
    private final E element;
    private final double weight;

    public Weighted(E instance, double weight) {
        this.element = instance;
        this.weight = weight;
    }


    public E getElement() {
        return element;
    }

    @Deprecated
    public double getFitness() {
        return weight;
    }

    public double getWeight() {
        return weight;
    }

    public <F> Weighted<F> map(Function<E,F> function) {
        return new Weighted<>(function.apply(element), weight);
    }


    @Override
    public String toString() {
        return "{" + weight + "}" + element;
    }
}

