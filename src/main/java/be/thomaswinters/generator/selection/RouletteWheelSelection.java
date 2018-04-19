package be.thomaswinters.generator.selection;

import be.thomaswinters.generator.fitness.IFitnessFunction;
import be.thomaswinters.generator.selection.data.AccumulatedMap;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.DoubleSupplier;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class RouletteWheelSelection<E> extends FitnessBasedSelector<E> {
    public RouletteWheelSelection(IFitnessFunction<E> fitnessFunction) {
        super(fitnessFunction);
    }

    @Override
    public Collection<E> select(Map<E, Double> map, int amount) {
        AccumulatedMap<E> accum = new AccumulatedMap<>(map);
        DoubleSupplier indexGenerator = () -> ThreadLocalRandom.current().nextDouble(accum.getTotalSize());
        return DoubleStream.generate(indexGenerator)
                .limit(amount)
                .mapToObj(accum::get)
                .collect(Collectors.toList());
    }
}
