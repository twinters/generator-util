package be.thomaswinters.generator.selection;

import be.thomaswinters.generator.fitness.IFitnessFunction;
import be.thomaswinters.generator.selection.data.AccumulatedMap;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.DoubleSupplier;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class RouletteWheelSelection<E> extends FitnessBasedSelector<E> {
    public RouletteWheelSelection(IFitnessFunction<E> fitnessFunction) {
        super(fitnessFunction);
    }

    public static <E> Optional<E> selectWeightedRouletteWheel(Stream<Weighted<? extends E>> stream) {
        Collection<? extends E> result = selectWeightedRouletteWheel(stream, 1);
        if (!result.isEmpty()) {
            return Optional.of(result.iterator().next());
        }
        return Optional.empty();

    }

    public static <E> Collection<E> selectWeightedRouletteWheel(Stream<? extends Weighted<? extends E>> stream, int amount) {
        AccumulatedMap<? extends E> accum = new AccumulatedMap<E>(stream);
        if (accum.getTotalSize() == 0) {
            System.err.println("Can't do roulette wheel if there are no candidates! " + accum);
            return Collections.emptyList();
        }
        DoubleSupplier indexGenerator = () -> ThreadLocalRandom.current()
                .nextDouble(accum.getTotalSize());
        return DoubleStream.generate(indexGenerator)
                .limit(amount)
                .mapToObj(accum::get)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<E> selectWeighted(Stream<Weighted<E>> stream, int amount) {
        return selectWeightedRouletteWheel(stream, amount);
    }
}
