package be.thomaswinters.generator.selection;

public class Weighted<E> {
    private final E instance;
    private final double weight;

    public Weighted(E instance, double weight) {
        this.instance = instance;
        this.weight = weight;
    }


    public E getElement() {

        return instance;
    }

    @Deprecated
    public double getFitness() {
        return weight;
    }

    public double getWeight() {
        return weight;
    }


    @Override
    public String toString() {
        return "{" + weight + "}" + instance;
    }
}

