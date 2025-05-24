package pl.zzpj.cleancode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

public class NumberOccurrenceCounter {

    Map<Integer, Integer> numberToOccurrence = new HashMap<>();
    private int maxValue = Integer.MIN_VALUE;
    private int minValue = Integer.MAX_VALUE;

    public void updateOccurrencesFor(List<Integer> numbers) {
        IntStream.range(0, numbers.size()).forEach(index -> updateOccurrencesFor(numbers.get(index)));
    }

    public void updateOccurrencesFor(Integer number) {
        validate(number);
        updateMap(number);
        updateMinAndMax(number);
    }

    public int getOccurrencesFor(int number) {
        return numberToOccurrence.getOrDefault(number, 0);
    }

    public double getAverage() {
        var weightedSum = numberToOccurrence
                .entrySet()
                .stream()
                .mapToDouble(entry -> entry.getKey() * entry.getValue())
                .sum();

        var weights = numberToOccurrence
                .values()
                .stream()
                .mapToDouble(Integer::doubleValue)
                .sum();

        return weightedSum / weights;
    }

    private void validate(Integer number) {
        Objects.requireNonNull(number);
    }

    private void updateMap(Integer number) {
        numberToOccurrence.merge(number, 1, Integer::sum);
    }

    private void updateMinAndMax(Integer number) {
        if (number > maxValue) {
            maxValue = number;
        }

        if (number < minValue) {
            minValue = number;
        }
    }
}