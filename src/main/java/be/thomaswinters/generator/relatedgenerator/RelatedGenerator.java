package be.thomaswinters.generator.relatedgenerator;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class RelatedGenerator<E,F> implements IRelatedGenerator<E,F> {
    private final Supplier<Optional<E>> generator;
    private final Function<F, Optional<E>> relatedGenerator;

    public RelatedGenerator(Supplier<Optional<E>> generator, Function<F, Optional<E>> relatedGenerator) {
        this.generator = generator;
        this.relatedGenerator = relatedGenerator;
    }

    @Override
    public Optional<E> generateRelated(F input) {
        return relatedGenerator.apply(input);
    }

    @Override
    public Optional<E> generate() {
        return generator.get();
    }

}
