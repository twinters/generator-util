package be.thomaswinters.generator.streamgenerator.reacting;

import be.thomaswinters.generator.generators.reacting.IReactingGenerator;
import be.thomaswinters.generator.selection.ISelector;
import be.thomaswinters.generator.selection.Weighted;
import be.thomaswinters.generator.streamgenerator.IStreamGenerator;

import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

@FunctionalInterface
public interface IReactingStreamGenerator<E, F> {
    Stream<E> generateStream(F input);

    default IStreamGenerator<E> seed(Supplier<F> inputSupplier) {
        return new SeededReactingStreamGenerator<>(this, inputSupplier);
    }

    default IReactingStreamGenerator<E, F> filter(Predicate<E> filter) {
        return input -> generateStream(input).filter(filter);
    }

    default IReactingStreamGenerator<E, F> filterUsingInput(BiPredicate<F, E> filter) {
        return input -> generateStream(input).filter(e -> filter.test(input, e));
    }


    default IReactingGenerator<E, F> reduceToGenerator(ISelector<E> selector) {
        return input -> selector.select(this.generateStream(input));
    }

    default IReactingStreamGenerator<Weighted<E>, F> weight(double i) {
        return input -> generateStream(input).map(e -> new Weighted<>(e, i));
    }

    default IReactingStreamGenerator<E, F> combineWith(IReactingStreamGenerator<E, F> other) {
        return input -> Stream.concat(generateStream(input), other.generateStream(input));
    }
}
