package be.thomaswinters.generator.generators.related;

import be.thomaswinters.generator.modifiers.ASelectionGenerator;
import be.thomaswinters.generator.selection.ISelector;

import java.util.Optional;

public class SelectionRelatedGenerator<E,F> extends ASelectionGenerator<E, IRelatedGenerator<E,F>> implements IRelatedGenerator<E,F> {
    public SelectionRelatedGenerator(IRelatedGenerator<E,F> innerGenerator, int amountOfGenerations, ISelector<E> selector) {
        super(innerGenerator, amountOfGenerations, selector);
    }

    @Override
    public Optional<E> generate(F input) {
        return select(() -> getInnerGenerator().generate(input));
    }
}
