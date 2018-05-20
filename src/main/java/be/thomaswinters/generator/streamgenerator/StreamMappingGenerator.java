package be.thomaswinters.generator.streamgenerator;

import java.util.function.Function;
import java.util.stream.Stream;

public class StreamMappingGenerator<E,F> implements IStreamGenerator<F> {
    private final IStreamGenerator<E> innerGenerator;
    private final Function<Stream<E>,Stream<F>> operator;

    public StreamMappingGenerator(IStreamGenerator<E> innerGenerator, Function<Stream<E>, Stream<F>> operator) {
        this.innerGenerator = innerGenerator;
        this.operator = operator;
    }

    @Override
    public Stream<F> generateStream() {
        return operator.apply(innerGenerator.generateStream());
    }
}
