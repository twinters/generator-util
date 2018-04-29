package be.thomaswinters.generator.selection;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PickBestComparatorSelector<E> implements ISelector<E> {
    private final Comparator<E> comparator;

    public PickBestComparatorSelector(Comparator<E> comparator) {
        this.comparator = comparator;
    }


    @Override
    public Collection<E> select(Stream<E> collection, int amount) {
        return collection
                .sorted(comparator)
                .limit(amount)
                .collect(Collectors.toList());
    }
}
