package be.thomaswinters.generator.streamgenerator.reacting;

import be.thomaswinters.generator.generators.IGenerator;
import be.thomaswinters.generator.generators.reacting.IReactingGenerator;
import be.thomaswinters.generator.selection.ISelector;
import be.thomaswinters.generator.streamgenerator.IStreamGenerator;

import java.util.function.Supplier;
import java.util.stream.Stream;

@FunctionalInterface
public interface IReactingStreamGenerator<E, F> {
    Stream<E> generateStream(F input);

    default IStreamGenerator<E> seed(Supplier<F> inputSupplier) {
        return new SeededReactingStreamGenerator<>(this, inputSupplier);
    }


    default IReactingGenerator<E,F> reduceToGenerator(ISelector<E> selector) {
        return input -> selector.select(this.generateStream(input));
    }

}
