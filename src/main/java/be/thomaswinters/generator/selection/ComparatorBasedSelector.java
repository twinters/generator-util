package be.thomaswinters.generator.selection;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public abstract class ComparatorBasedSelector<E> implements ISelector<E> {
    private final Comparator<E> comparator;
    private final Function<Integer, Double> rankingFunction;

    public ComparatorBasedSelector(Comparator<E> comparator, Function<Integer, Double> rankingFunction) {
        this.comparator = comparator;
        this.rankingFunction = rankingFunction;
    }

    @Override
    public Collection<E> select(Stream<E> stream, int amount) {
        List<E> list = stream.sorted(comparator).collect(Collectors.toList());
        int listSize = list.size();
        Stream<Weighted<E>> weightedStream =
                IntStream.range(0, listSize)
                        .mapToObj(i -> new Weighted<>(list.get(i), rankingFunction.apply(listSize - 1 - i)));

        return selectWeighted(weightedStream, amount);
    }

    /**
     * Selects an amount of elements based on their fitness
     *
     * @param map    The map containing the list of items to select from with their fitness
     * @param amount The amount to select
     * @return A collection containing exactly as many items as required by "amount"
     */
    public abstract Collection<E> selectWeighted(Stream<Weighted<E>> map, int amount);


}
