package be.thomaswinters.generator.relatedgenerator;

import java.util.Optional;

public abstract class RelatedGeneratorDecorator<E> implements IRelatedGenerator<E> {
    private IRelatedGenerator<E> innerGenerator;

    public RelatedGeneratorDecorator(IRelatedGenerator<E> innerGenerator) {
        this.innerGenerator = innerGenerator;
    }

    protected IRelatedGenerator<E> getInnerGenerator() {
        return innerGenerator;
    }

    @Override
    public Optional<E> generateRelated(E input) {
        return innerGenerator.generateRelated(input);
    }

    @Override
    public Optional<E> generate() {
        return innerGenerator.generate();
    }
}
