package be.thomaswinters.generator.selection;

import java.util.Collection;
import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Stream;

public class RouletteWheelComparatorSelection<E> extends ComparatorBasedSelector<E> {

    public static Function<Integer, Double> SIMPLE_POSITION_RANKER = i -> (double) i + 1;

    public RouletteWheelComparatorSelection(Comparator<E> comparator, Function<Integer, Double> rankingFunction) {
        super(comparator, rankingFunction);
    }

    public RouletteWheelComparatorSelection(Comparator<E> comparator) {
        super(comparator, SIMPLE_POSITION_RANKER);
    }

    @Override
    public Collection<E> selectWeighted(Stream<Weighted<E>> stream, int amount) {
        return RouletteWheelSelection.selectWeightedRouletteWheel(stream, amount);
    }

}
