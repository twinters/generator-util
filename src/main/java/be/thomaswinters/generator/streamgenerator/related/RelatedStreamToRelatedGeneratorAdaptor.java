package be.thomaswinters.generator.streamgenerator.related;

import be.thomaswinters.generator.generators.related.IRelatedGenerator;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class RelatedStreamToRelatedGeneratorAdaptor<E,F> implements IRelatedGenerator<E,F> {
    private final IRelatedStreamGenerator<E,F> innerStreamGenerator;
    private final Function<Stream<E>, Optional<E>> mapper;

    public RelatedStreamToRelatedGeneratorAdaptor(IRelatedStreamGenerator<E,F> innerStreamGenerator, Function<Stream<E>, Optional<E>> mapper) {
        this.innerStreamGenerator = innerStreamGenerator;
        this.mapper = mapper;
    }

    @Override
    public Optional<E> generate() {
        return mapper.apply(innerStreamGenerator.generateStream());
    }

    @Override
    public Optional<E> generateRelated(F input) {
        return mapper.apply(innerStreamGenerator.generateStream(input));
    }
}