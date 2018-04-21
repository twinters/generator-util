package be.thomaswinters.generator.selection;

import be.thomaswinters.generator.fitness.IFitnessFunction;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class PickBestSelector<E> extends FitnessBasedSelector<E> {
    public PickBestSelector(IFitnessFunction<E> fitnessFunction) {
        super(fitnessFunction);
    }

    @Override
    public Collection<E> selectWeighted(List<Weighted<E>> list, int amount) {
        return list
                .stream()
                .sorted((e, f) -> (int) Math.signum(e.getFitness() - f.getFitness()))
                .limit(amount)
                .map(Weighted::getElement)
                .collect(Collectors.toList());
    }
}
