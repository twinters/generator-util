package be.thomaswinters.generator.related;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class RelatedGenerator<E> implements IRelatedGenerator<E> {
    private final Supplier<Optional<E>> generator;
    private final Function<E, Optional<E>> relatedGenerator;

    public RelatedGenerator(Supplier<Optional<E>> generator, Function<E, Optional<E>> relatedGenerator) {
        this.generator = generator;
        this.relatedGenerator = relatedGenerator;
    }

    @Override
    public Optional<E> generateRelated(E input) {
        return relatedGenerator.apply(input);
    }

    @Override
    public Optional<E> generate() {
        return generator.get();
    }

}
