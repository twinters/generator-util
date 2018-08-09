package be.thomaswinters.generator.generators;

import be.thomaswinters.generator.selection.RouletteWheelSelection;
import be.thomaswinters.generator.selection.Weighted;
import com.google.common.collect.ImmutableList;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GeneratorCombiner<E> implements IGenerator<E> {
    private final ImmutableList<Weighted<IGenerator<E>>> generators;

    public GeneratorCombiner(List<Weighted<IGenerator<E>>> generators) {
        this.generators = ImmutableList.copyOf(generators);
    }

    public static <E> GeneratorCombiner<E> fromGenerators(List<IGenerator<E>> generators) {
        return new GeneratorCombiner<>(
                generators
                        .stream()
                        .map(e -> new Weighted<>(e, 1))
                        .collect(Collectors.toList()));
    }

    @SafeVarargs
    public static <E> GeneratorCombiner<E> fromGenerators(IGenerator<E>... generators) {
        return fromGenerators(Arrays.asList(generators));
    }

    @Override
    public Optional<E> generate() {
        Stream<Weighted<E>> stream = generators
                .stream()
                .map(e -> e.map(IGenerator::generate))
                .filter(e -> e.getElement().isPresent())
                .map(e -> e.map(Optional::get));
        return RouletteWheelSelection.selectWeightedRouletteWheel(
                stream, 1).stream().findFirst();
    }
}
