package be.thomaswinters.generator.related;

import be.thomaswinters.generator.selection.ISelector;
import be.thomaswinters.generator.stream.ASelectionGenerator;

import java.util.Optional;

public class SelectionRelatedGenerator<E> extends ASelectionGenerator<E, IRelatedGenerator<E>> implements IRelatedGenerator<E> {
    public SelectionRelatedGenerator(IRelatedGenerator<E> innerGenerator, ISelector<E> selector, int amountOfGenerations) {
        super(innerGenerator, selector, amountOfGenerations);
    }

    @Override
    public Optional<E> generateRelated(E input) {
        return select(() -> getInnerGenerator().generateRelated(input));
    }
}
