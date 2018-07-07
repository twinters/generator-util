package be.thomaswinters.generator.generators.reacting;

import be.thomaswinters.generator.generators.IGenerator;
import be.thomaswinters.generator.streamgenerator.IStreamGenerator;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;

@FunctionalInterface
public interface IReactingGenerator<E, F> extends Function<F, Optional<E>> {
    Optional<E> generateRelated(F input);

    @Override
    default Optional<E> apply(F input) {
        return generateRelated(input);
    }

    default IGenerator<E> toGenerator(IGenerator<F> seeder) {
        return () -> {
            Optional<F> generated = seeder.generate();
            if (generated.isPresent()) {
                return generateRelated(generated.get());
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
        return input -> this.generateRelated(mapper.apply(input));
    }

    default <G> IReactingGenerator<G, F> map(Function<E, G> mapper) {
        return input -> this.generateRelated(input).map(mapper);
    }

    default IReactingGenerator<E, F> filter(int maxTrials, Predicate<E> filter) {
        return (F input) -> IntStream.range(0, maxTrials)
                .mapToObj(x -> generateRelated(input))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(filter)
                .findFirst();
    }

    default IReactingGenerator<E, F> orElse(IReactingGenerator<E, F> alternative) {
        return input -> this.generateRelated(input)
                .or(() -> alternative.generateRelated(input));
    }

    default IReactingGenerator<E, F> retry(int maxAmountOfTimes) {
        return input -> {
            for (int i = 0; i < maxAmountOfTimes; i++) {
                Optional<E> result = this.generateRelated(input);
                if (result.isPresent()) {
                    return result;
                }
            }
            return Optional.empty();
        };
    }
}
