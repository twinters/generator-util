package be.thomaswinters.generator.generators;

import be.thomaswinters.generator.selection.RouletteWheelSelection;
import be.thomaswinters.generator.selection.Weighted;
import com.google.common.collect.ImmutableList;

import java.util.Optional;
import java.util.stream.Stream;

public class GeneratorCombiner<E> implements IGenerator<E> {
    private final ImmutableList<Weighted<IGenerator<E>>> generators;

    public GeneratorCombiner(ImmutableList<Weighted<IGenerator<E>>> generators) {
        this.generators = generators;
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
