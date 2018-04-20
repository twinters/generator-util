package be.thomaswinters.generator.selection;

import java.util.Collection;
import java.util.List;

@FunctionalInterface
public interface ISelector<E> {
    default E select(List<E> collection) {
        return select(collection, 1).iterator().next();
    }

    Collection<E> select(List<E> collection, int amount);
}
