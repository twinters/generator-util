package be.thomaswinters.generator.related;

import be.thomaswinters.generator.selection.ISelector;
import be.thomaswinters.generator.modifiers.ASelectionGenerator;

import java.util.Optional;

public class SelectionRelatedGenerator<E> extends ASelectionGenerator<E, IRelatedGenerator<E>> implements IRelatedGenerator<E> {
    public SelectionRelatedGenerator(IRelatedGenerator<E> innerGenerator, int amountOfGenerations, ISelector<E> selector) {
        super(innerGenerator, amountOfGenerations, selector);
    }

    @Override
    public Optional<E> generateRelated(E input) {
        return select(() -> getInnerGenerator().generateRelated(input));
    }
}
