package be.thomaswinters.generator.fitness;

import java.util.function.DoubleUnaryOperator;

public class FitnessModifier<E> implements IFitnessFunction<E> {
    private final IFitnessFunction<E> innerFitnessFunction;
    private final DoubleUnaryOperator operator;

    public FitnessModifier(IFitnessFunction<E> innerFitnessFunction, DoubleUnaryOperator operator) {
        this.innerFitnessFunction = innerFitnessFunction;
        this.operator = operator;
    }

    @Override
    public double getFitness(E input) {
        return operator.applyAsDouble(innerFitnessFunction.getFitness(input));
    }
}
