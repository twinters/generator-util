package be.thomaswinters.generator.selection;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@FunctionalInterface
public interface ISelector<E> {
    default Optional<E> select(List<E> collection) {
        Iterator<E> it = select(collection, 1).iterator();
        return it.hasNext() ? Optional.of(it.next()) : Optional.empty();
    }

    Collection<E> select(List<E> collection, int amount);
}
