package be.thomaswinters.generator.generators.reacting;

import be.thomaswinters.generator.generators.IGenerator;
import be.thomaswinters.generator.streamgenerator.IStreamGenerator;
import be.thomaswinters.generator.streamgenerator.reacting.IReactingStreamGenerator;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;

@FunctionalInterface
public interface IReactingGenerator<E, F> extends Function<F, Optional<E>> {
    @Deprecated
    default Optional<E> generateRelated(F input) {
        return generate(input);
    }

    Optional<E> generate(F input);

    @Override
    default Optional<E> apply(F input) {
        return generate(input);
    }

    default IGenerator<E> toGenerator(IGenerator<F> seeder) {
        return () -> {
            Optional<F> generated = seeder.generate();
            if (generated.isPresent()) {
                return generate(generated.get());
            }
            return Optional.empty();
        };
    }

    default IStreamGenerator<E> reactToStreamGenerator(IStreamGenerator<F> streamGenerator) {
        return () -> streamGenerator
                .generateStream()
                .map(this::generateRelated)
                .filter(Optional::isPresent)
                .map(Optional::get);
    }

    default <G> IReactingGenerator<E, G> mapFrom(Function<G, F> mapper) {
        return input -> this.generate(mapper.apply(input));
    }

    default <G> IReactingGenerator<G, F> map(Function<E, G> mapper) {
        return input -> this.generate(input).map(mapper);
    }

    default IReactingGenerator<E, F> filter(int maxTrials, Predicate<E> filter) {
        return (F input) -> IntStream.range(0, maxTrials)
                .mapToObj(x -> generate(input))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(filter)
                .findFirst();
    }

    default IReactingGenerator<E, F> orElse(IReactingGenerator<E, F> alternative) {
        return input -> this.generate(input)
                .or(() -> alternative.generate(input));
    }

    default IReactingGenerator<E, F> retry(int maxAmountOfTimes) {
        return input -> {
            for (int i = 0; i < maxAmountOfTimes; i++) {
                Optional<E> result = this.generate(input);
                if (result.isPresent()) {
                    return result;
                }
            }
            return Optional.empty();
        };
    }

    default IReactingStreamGenerator<E, F> toReactingStreamGenerator() {
        return input -> this.generate(input).stream();
    }
}
