package be.thomaswinters.generator.generators;

import be.thomaswinters.generator.selection.ISelector;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SelectionGenerator<E> implements IGenerator<E> {
    private final IGenerator<E> innerGenerator;
    private final ISelector<E> selector;
    private final int amountOfGenerations;

    public SelectionGenerator(IGenerator<E> innerGenerator, ISelector<E> selector, int amountOfGenerations) {
        this.innerGenerator = innerGenerator;
        this.selector = selector;
        this.amountOfGenerations = amountOfGenerations;
    }

    @Override
    public Optional<E> generate() {
        List<E> choices = Stream.generate(innerGenerator::generate)
                .limit(amountOfGenerations)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        if (choices.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(selector.select(choices));
    }
}
