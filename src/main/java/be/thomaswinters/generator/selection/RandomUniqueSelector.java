package be.thomaswinters.generator.selection;

import be.thomaswinters.random.Picker;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RandomUniqueSelector<E> implements ISelector<E> {

    @Override
    public Collection<E> select(Stream<E> collection, int amount) {
        return Picker.pick(collection.collect(Collectors.toList()), amount);
    }
}
