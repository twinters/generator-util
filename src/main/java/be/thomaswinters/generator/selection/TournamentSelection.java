package be.thomaswinters.generator.selection;

import be.thomaswinters.generator.fitness.IFitnessFunction;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TournamentSelection<E> extends FitnessBasedSelector<E> {
    private final int tournamentSize;

    public TournamentSelection(IFitnessFunction<E> fitnessFunction, int tournamentSize) {
        super(fitnessFunction);
        this.tournamentSize = tournamentSize;
    }

    @Override
    public Collection<E> selectWeighted(Stream<Weighted<E>> stream, int amount) {
        List<Weighted<E>> list = stream.collect(Collectors.toList());
        if (list.isEmpty()) {
            throw new IllegalArgumentException("Map can not be empty:" + list);
        }
        IntSupplier generator = () -> ThreadLocalRandom.current().nextInt(list.size());

        // Amount of times
        return IntStream.range(0, amount)
                .mapToObj(x ->
                        // Generate tournament
                        IntStream.generate(generator)
                                .limit(tournamentSize)
                                .mapToObj(list::get)
                                // Find winner of tournament
                                .max(Comparator.comparingDouble(Weighted::getFitness))
                                .map(Weighted::getElement)
                                .get())
                .collect(Collectors.toList());
    }

}
