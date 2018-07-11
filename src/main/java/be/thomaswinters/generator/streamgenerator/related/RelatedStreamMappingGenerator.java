package be.thomaswinters.generator.streamgenerator.related;

import java.util.function.Function;
import java.util.stream.Stream;

public class RelatedStreamMappingGenerator<E, F, G> implements IRelatedStreamGenerator<G,F> {
    private final IRelatedStreamGenerator<E,F> innerGenerator;
    private final Function<Stream<E>, Stream<G>> operator;

    public RelatedStreamMappingGenerator(IRelatedStreamGenerator<E,F> innerGenerator, Function<Stream<E>, Stream<G>> operator) {
        this.innerGenerator = innerGenerator;
        this.operator = operator;
    }

    @Override
    public Stream<G> generateStream(F input) {
        return operator.apply(innerGenerator.generateStream(input));
    }

    @Override
    public Stream<G> generateStream() {return operator.apply(innerGenerator.generateStream());
    }
}
