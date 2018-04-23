package be.thomaswinters.generator.selection;

import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Stream;

@FunctionalInterface
public interface ISelector<E> {
    default Optional<E> select(Stream<E> collection) {
        Iterator<E> it = select(collection, 1).iterator();
        return it.hasNext() ? Optional.of(it.next()) : Optional.empty();
    }

    Collection<E> select(Stream<E> collection, int amount);
}
