package be.thomaswinters.generator.generators.reacting;

import be.thomaswinters.generator.generators.IGenerator;
import be.thomaswinters.generator.streamgenerator.IStreamGenerator;

import java.util.Optional;
import java.util.function.Function;

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
}
