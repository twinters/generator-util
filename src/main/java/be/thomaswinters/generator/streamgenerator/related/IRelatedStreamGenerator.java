package be.thomaswinters.generator.streamgenerator.related;

import be.thomaswinters.generator.generators.related.IRelatedGenerator;
import be.thomaswinters.generator.streamgenerator.IStreamGenerator;
import be.thomaswinters.generator.streamgenerator.reacting.IReactingStreamGenerator;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public interface IRelatedStreamGenerator<E,F> extends IStreamGenerator<E>, IReactingStreamGenerator<E,F> {

    // TO ISTREAMGENERATOR
    @Override
    default <G> IRelatedStreamGenerator<G,F> mapStream(Function<Stream<E>, Stream<G>> mapper) {
        return new RelatedStreamMappingGenerator<>(this, mapper);
    }

    @Override
    default <G> IRelatedStreamGenerator<G,F> map(Function<E, G> operator) {
        return mapStream(stream -> stream.map(operator));
    }

    @Override
    default IRelatedStreamGenerator<E,F> filter(Predicate<E> operator) {
        return mapStream(stream -> stream.filter(operator));
    }

    @Override
    default IRelatedStreamGenerator<E,F> sort(Comparator<E> comparator) {
        return mapStream(stream -> stream.sorted(comparator));
    }


    // TO IGENERATOR
    @Override
    default IRelatedGenerator<E,F> reduceToGenerator(Function<Stream<E>, Optional<E>> reducer) {
        return new RelatedStreamToRelatedGeneratorAdaptor<>(this, reducer);
    }

    @Override
    default IRelatedGenerator<E,F> findFirst() {
        return reduceToGenerator(Stream::findFirst);
    }

    @Override
    default IRelatedGenerator<E,F> max(Comparator<E> comparator) {
        return reduceToGenerator(stream -> stream.max(comparator));
    }

    @Override
    default IRelatedGenerator<E,F> min(Comparator<E> comparator) {
        return reduceToGenerator(stream -> stream.min(comparator));
    }


}
