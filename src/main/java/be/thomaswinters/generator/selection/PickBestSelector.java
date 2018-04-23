package be.thomaswinters.generator.selection;

import be.thomaswinters.generator.fitness.IFitnessFunction;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PickBestSelector<E> extends FitnessBasedSelector<E> {
    public PickBestSelector(IFitnessFunction<E> fitnessFunction) {
        super(fitnessFunction);
    }


    @Override
    public Collection<E> selectWeighted(Stream<Weighted<E>> list, int amount) {
        return list
                .sorted((e, f) -> -1 * Double.compare(e.getFitness(), f.getFitness()))
                .limit(amount)
                .map(Weighted::getElement)
                .collect(Collectors.toList());
    }
}
