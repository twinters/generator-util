package be.thomaswinters.text.generator;

import be.thomaswinters.generator.generators.IGenerator;

import java.util.Optional;

public interface ITextGenerator extends IGenerator<String> {
    Optional<String> generateText();

    @Override
    default Optional<String> get() {
        return generateText();
    }
}
