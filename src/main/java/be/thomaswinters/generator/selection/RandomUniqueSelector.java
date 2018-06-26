package be.thomaswinters.generator.selection;

import be.thomaswinters.random.Picker;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RandomSelector<E> implements ISelector<E> {

    @Override
    public Collection<E> selectWeighted(Stream<Weighted<E>> list, int amount) {
        return list
                .sorted((e, f) -> -1 * Double.compare(e.getFitness(), f.getFitness()))
                .limit(amount)
                .map(Weighted::getElement)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<E> select(Stream<E> collection, int amount) {
        return Picker.pick(collection.collect(Collectors.toList()), amount);
    }
}
