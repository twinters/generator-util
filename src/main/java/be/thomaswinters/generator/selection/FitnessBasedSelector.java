package be.thomaswinters.generator.selection;

import be.thomaswinters.generator.fitness.IFitnessFunction;

import java.util.Collection;
import java.util.stream.Stream;

public abstract class FitnessBasedSelector<E> implements ISelector<E> {
    private final IFitnessFunction<E> fitnessFunction;

    public FitnessBasedSelector(IFitnessFunction<E> fitnessFunction) {
        this.fitnessFunction = fitnessFunction;
    }

    @Override
    public Collection<E> select(Stream<E> list, int amount) {
        return selectWeighted(list.map(e -> new Weighted<>(e, fitnessFunction.getFitness(e))), amount);
    }

    /**
     * Selects an amount of elements based on their fitness
     *
     * @param map    The map containing the list of items to select from with their fitness
     * @param amount The amount to select
     * @return A collection containing exactly as many items as required by "amount"
     */
    public abstract Collection<E> selectWeighted(Stream<Weighted<E>> map, int amount);


}
