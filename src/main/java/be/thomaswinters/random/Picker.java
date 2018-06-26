package be.thomaswinters.random;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Picker {

    private static final Random RANDOM = new Random();

    public static <E> E pick(Collection<E> values) {
        return new ArrayList<E>(values).get(RANDOM.nextInt(values.size()));
    }

    public static <E> List<E> pick(Collection<E> values, int amount) {
        List<E> list = new ArrayList<>(values);
        return pickRandomUniqueIndices(amount, values.size())
                .stream()
                .map(list::get)
                .collect(Collectors.toList());
    }

    public static <E> Optional<E> pickOptional(Collection<E> values) {
        return values.isEmpty() ? Optional.empty() : Optional.of(pick(values));
    }

    public static <E> List<E> pickAtMost(Collection<E> values, int amount) {
        return pick(values, Integer.min(amount, values.size()));
    }

    /**
     * Use this function if you want some input converted into an output with a
     * certain chance, and return defaultOutput if the event failed.
     *
     * @param input         The input to process
     * @param event         The converting event to happen
     * @param defaultOutput The output to output when the event failed.
     * @param chance        The chance it will happen. Must be in the interval [0,1]
     * @return
     */
    public static <I, O> O chanceEvent(I input, Function<I, O> event, O defaultOutput, double chance) {
        if (chance < 0 || chance > 1) {
            throw new IllegalArgumentException("The chance of the event must be between 0 and 1");
        }
        if (chance > RANDOM.nextDouble()) {
            return event.apply(input);
        }
        return defaultOutput;
    }

    /**
     * Java's Random does not has a "Next Long" :c
     *
     * @param n
     * @return
     */
    public static long nextLong(long n) {
        long bits, val;
        do {
            bits = (RANDOM.nextLong() << 1) >>> 1;
            val = bits % n;
        } while (bits - val + (n - 1) < 0L);
        return val;
    }

    public static void setSeed(long number) {
        RANDOM.setSeed(number);
    }

    public static int betweenInclusive(int min, int max) {
        if (max < min) {
            throw new IllegalArgumentException("Minimum should be lower than maximum");
        }
        return min + RANDOM.nextInt(max + 1 - min);
    }

    public static <E> E pickWeighted(Map<E, Integer> map) {

        int totalSize = map.values().stream().mapToInt(e -> e).sum();

        int chosenIndex = RANDOM.nextInt(totalSize);

        int currentIndex = 0;
        for (Map.Entry<E, Integer> entry : map.entrySet()) {
            currentIndex += entry.getValue();
            if (chosenIndex < currentIndex) {
                return entry.getKey();
            }
        }
        throw new IllegalStateException("Not able to pick a random element from " + map);
    }


    public static Set<Integer> pickConsequtiveIndices(int amount, int length) {
        if (amount > length) {
            throw new IllegalArgumentException("Amount must be smaller than the length");
        }
        int start = RANDOM.nextInt(length - amount);
        Set<Integer> result = new HashSet<Integer>(amount);
        for (int i = 0; i < amount; i++) {
            result.add(start + i);
        }
        return result;

    }

    public static Set<Integer> pickRandomUniqueIndices(int amount, int length) {
        if (amount > length) {
            throw new IllegalArgumentException("Amount must be smaller than the length");
        }
        Set<Integer> result = new HashSet<Integer>();

        for (int i = 0; i < amount; i++) {
            int randomInt = RANDOM.nextInt(length - i);
            int skipAmount = calculateSkipAmount(result, randomInt);
            result.add(randomInt + skipAmount);
        }

        return result;
    }

    public static int calculateSkipAmount(Set<Integer> indices, int possibleIndex) {
        int skipAmount = 0;
        int previous = skipAmount;
        do {
            previous = skipAmount;
            final int totalIndex = possibleIndex + skipAmount;
            skipAmount = (int) indices.stream().filter(e -> e <= totalIndex).mapToInt(e -> e).count();

        } while (skipAmount != previous);
        return skipAmount;
    }
}
