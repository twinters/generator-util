package be.thomaswinters.generator.streamgenerator;

import be.thomaswinters.generator.generators.IGenerator;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class StreamToGeneratorAdaptor<E> implements IGenerator<E> {
    private final IStreamGenerator<E> innerStreamGenerator;
    private final Function<Stream<E>, Optional<E>> mapper;

    public StreamToGeneratorAdaptor(IStreamGenerator<E> innerStreamGenerator, Function<Stream<E>, Optional<E>> mapper) {
        this.innerStreamGenerator = innerStreamGenerator;
        this.mapper = mapper;
    }

    @Override
    public Optional<E> generate() {
        return mapper.apply(innerStreamGenerator.generateStream());
    }
}
