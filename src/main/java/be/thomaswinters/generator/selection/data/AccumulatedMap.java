package be.thomaswinters.generator.selection.data;

import be.thomaswinters.generator.selection.Weighted;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.Ordering;

import java.util.List;
import java.util.NavigableMap;

public class AccumulatedMap<E> {
    private final double totalSize;
    private final NavigableMap<Double, E> accumulatedMap;

    public AccumulatedMap(List<Weighted<E>> list) {
        ImmutableSortedMap.Builder<Double, E> b =
                new ImmutableSortedMap.Builder<Double, E>(Ordering.natural());
        double currentTotal = 0d;
        for (Weighted<E> entry : list) {
            currentTotal += entry.getFitness();
            b.put(currentTotal, entry.getElement());
        }

        // Initialise
        this.accumulatedMap = b.build();
        this.totalSize = currentTotal;
    }

    public E get(Double index) {
        return accumulatedMap.ceilingEntry(index).getValue();
    }

    public double getTotalSize() {
        return totalSize;
    }
}
