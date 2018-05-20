package be.thomaswinters.generator.streamgenerator.reacting;

import be.thomaswinters.generator.streamgenerator.IStreamGenerator;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class SeededReactingStreamGenerator<E,F> implements IStreamGenerator<E> {
    private final IReactingStreamGenerator<E,F> innerGenerator;
    private final Supplier<F> seeder;

    public SeededReactingStreamGenerator(IReactingStreamGenerator<E, F> innerGenerator, Supplier<F> seeder) {
        this.innerGenerator = innerGenerator;
        this.seeder = seeder;
    }

    @Override
    public Stream<E> generateStream() {
        return innerGenerator.generateStream(seeder.get());
    }
}
