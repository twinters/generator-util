package be.thomaswinters.generator.generators;

import be.thomaswinters.generator.selection.ISelector;
import be.thomaswinters.generator.modifiers.ASelectionGenerator;

import java.util.Optional;
import java.util.function.Supplier;

public class SelectionGenerator<E> extends ASelectionGenerator<E, Supplier<Optional<E>>> {
    public SelectionGenerator(Supplier<Optional<E>> innerGenerator, int amountOfGenerations, ISelector<E> selector) {
        super(innerGenerator, amountOfGenerations, selector);
    }

}
