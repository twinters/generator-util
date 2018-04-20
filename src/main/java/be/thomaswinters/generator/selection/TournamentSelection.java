package be.thomaswinters.generator.selection;

import be.thomaswinters.generator.fitness.IFitnessFunction;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TournamentSelection<E> extends FitnessBasedSelector<E> {
    private final int tournamentSize;

    public TournamentSelection(IFitnessFunction<E> fitnessFunction, int tournamentSize) {
        super(fitnessFunction);
        this.tournamentSize = tournamentSize;
    }

    @Override
    public Collection<E> select(Map<E, Double> map, int amount) {
        if (map.isEmpty()) {
            throw new IllegalArgumentException("Map can not be empty:" + map);
        }
        List<Entry<E, Double>> keys = new ArrayList<>(map.entrySet());
        IntSupplier generator = () -> ThreadLocalRandom.current().nextInt(keys.size());

        // Amount of times
        return IntStream.range(0, amount)
                .mapToObj(x ->
                        // Generate tournament
                        IntStream.generate(generator)
                                .limit(tournamentSize)
                                .mapToObj(keys::get)
                                // Find winner of tournament
                                .max(Comparator.comparingDouble(Entry::getValue))
                                .map(Entry::getKey)
                                .get())
                .collect(Collectors.toList());
    }

}
