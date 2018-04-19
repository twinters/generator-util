package be.thomaswinters.generator.selection.data;

import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.Ordering;

import java.util.Map;
import java.util.NavigableMap;

public class AccumulatedMap<E> {
    private final double totalSize;
    private final NavigableMap<Double, E> accumulatedMap;

    public AccumulatedMap(Map<E, Double> map) {
        ImmutableSortedMap.Builder<Double, E> b =
                new ImmutableSortedMap.Builder<Double, E>(Ordering.natural());
        double currentTotal = 0d;
        for (Map.Entry<E, Double> entry : map.entrySet()) {
            currentTotal += entry.getValue();
            b.put(currentTotal, entry.getKey());
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
