package be.thomaswinters.generator.streamgenerator;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

@FunctionalInterface
public interface IStreamGenerator<E> {
    Stream<E> generateStream();

    default <F> IStreamGenerator<F> map(Function<E,F> operator) {
        return mapStream(stream->stream.map(operator));
    }

    default IStreamGenerator<E> filter(Predicate<E> operator) {
        return mapStream(stream->stream.filter(operator));
    }

    default IStreamGenerator<E> sort(Comparator<E> comparator) {
        return mapStream(stream->stream.sorted(comparator));
    }

    default <F> IStreamGenerator<F> mapStream(Function<Stream<E>, Stream<F>> mapper) {
        return new StreamMappingGenerator<>(this, mapper);
    }

}
