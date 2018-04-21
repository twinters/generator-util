package be.thomaswinters.generator.selection;

import be.thomaswinters.generator.fitness.IFitnessFunction;
import be.thomaswinters.generator.selection.data.AccumulatedMap;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.DoubleSupplier;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class RouletteWheelSelection<E> extends FitnessBasedSelector<E> {
    public RouletteWheelSelection(IFitnessFunction<E> fitnessFunction) {
        super(fitnessFunction);
    }

    @Override
    public Collection<E> selectWeighted(List<Weighted<E>> list, int amount) {
        AccumulatedMap<E> accum = new AccumulatedMap<E>(list);
        DoubleSupplier indexGenerator = () -> ThreadLocalRandom.current()
                .nextDouble(accum.getTotalSize());
        return DoubleStream.generate(indexGenerator)
                .limit(amount)
                .mapToObj(accum::get)
                .collect(Collectors.toList());
    }
}
