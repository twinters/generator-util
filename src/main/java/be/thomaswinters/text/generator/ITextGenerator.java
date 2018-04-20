package be.thomaswinters.text.generator;

import be.thomaswinters.generator.generators.IGenerator;

import java.util.Optional;

@FunctionalInterface
public interface ITextGenerator extends IGenerator<String> {
    Optional<String> generateText();

    @Override
    default Optional<String> generate() {
        return generateText();
    }
}
