package be.thomaswinters.generator.selection;

import be.thomaswinters.generator.fitness.IFitnessFunction;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class PickBestSelector<E> extends FitnessBasedSelector<E> {
    public PickBestSelector(IFitnessFunction<E> fitnessFunction) {
        super(fitnessFunction);
    }

    @Override
    public Collection<E> select(Map<E, Double> map, int amount) {
        return map.entrySet()
                .stream()
                .sorted((e, f) -> (int) Math.signum(e.getValue() - f.getValue()))
                .limit(amount)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
