package be.thomaswinters.generator.streamgenerator;

import be.thomaswinters.generator.generators.IGenerator;
import be.thomaswinters.generator.selection.ISelector;
import be.thomaswinters.generator.streamgenerator.reacting.IReactingStreamGenerator;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class ReactingStreamGenerator<E, F> implements IReactingStreamGenerator<E, F> {
    private final Function<F, Stream<E>> function;

    public ReactingStreamGenerator(Function<F, Stream<E>> function) {
        this.function = function;
    }

    public static <E, F> IReactingStreamGenerator<E, F> fromListFunction(Function<F, List<E>> function) {
        return new ReactingStreamGenerator<>(input -> function.apply(input).stream());
    }

    @Override
    public Stream<E> generateStream(F input) {
        return function.apply(input);
    }

}
