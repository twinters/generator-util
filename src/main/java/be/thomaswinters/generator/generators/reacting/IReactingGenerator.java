package be.thomaswinters.generator.generators.reacting;

import java.util.Optional;
import java.util.function.Function;

@FunctionalInterface
public interface IReactingGenerator<E,F> extends Function<F, Optional<E>> {
    Optional<E> generateRelated(F input);

    @Override
    default Optional<E> apply(F input) {
        return generateRelated(input);
    }
}
