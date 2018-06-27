package be.thomaswinters.generator.streamgenerator.reacting;

import be.thomaswinters.generator.streamgenerator.IStreamGenerator;

import java.util.function.Supplier;
import java.util.stream.Stream;

@FunctionalInterface
public interface IReactingStreamGenerator<E,F> {
    Stream<E> generateStream(F input);

    default IStreamGenerator<E> seed(Supplier<F> inputSupplier) {
        return new SeededReactingStreamGenerator<>(this,inputSupplier);
    }

}
