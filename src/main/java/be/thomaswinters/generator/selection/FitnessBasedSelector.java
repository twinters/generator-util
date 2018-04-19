package be.thomaswinters.generator.selection;

import be.thomaswinters.generator.fitness.IFitnessFunction;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class FitnessBasedSelector<E> implements ISelector<E> {
    private final IFitnessFunction<E> fitnessFunction;

    public FitnessBasedSelector(IFitnessFunction<E> fitnessFunction) {
        this.fitnessFunction = fitnessFunction;
    }

    @Override
    public Collection<E> select(List<E> collection, int amount) {
        return select(collection.stream().collect(
                Collectors.toMap(Function.identity(), fitnessFunction::getFitness)), amount);
    }

    /**
     * Selects an amount of elements based on their fitness
     *
     * @param map    The map containing the list of items to select from with their fitness
     * @param amount The amount to select
     * @return A collection containing exactly as many items as required by "amount"
     */
    public abstract Collection<E> select(Map<E, Double> map, int amount);


}
