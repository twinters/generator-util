package be.thomaswinters.generator.selection;

import be.thomaswinters.generator.generators.IGenerator;

import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Stream;

@FunctionalInterface
public interface ISelector<E> {
    default IGenerator<E> toGenerator(Stream<E> collection) {
        return ()->select(collection);
    }

    default Optional<E> select(Stream<E> collection) {
        Iterator<E> it = select(collection, 1).iterator();
        return it.hasNext() ? Optional.of(it.next()) : Optional.empty();
    }

    Collection<E> select(Stream<E> collection, int amount);
}
