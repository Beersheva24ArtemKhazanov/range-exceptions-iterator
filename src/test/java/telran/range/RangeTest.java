package telran.range;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import telran.range.exceptions.OutOfRangeMaxValueException;
import telran.range.exceptions.OutOfRangeMinValueException;

public class RangeTest {
    private static final int MIN = 50;
    private static final int MAX = 100;
    Range range = Range.getRange(MIN, MAX);

    @Test
    void wrongRangeCreatingTest() {
        assertThrowsExactly(IllegalArgumentException.class, () -> Range.getRange(MAX, MIN));
    }

    @Test
    void rightNumberTest() throws OutOfRangeMaxValueException, OutOfRangeMinValueException {
        range.checkNumber(55);
    }

    @Test
    void wrongNumberTest() throws Exception {
        range.checkNumber(55);
        assertThrowsExactly(OutOfRangeMaxValueException.class, () -> range.checkNumber(MAX + 1));
        assertThrowsExactly(OutOfRangeMinValueException.class, () -> range.checkNumber(MIN - 1));
    }

    @Test
    void iteratorTest() {
        final int MAX = 42;
        final int MIN = 1;
        Range rangeIt = Range.getRange(MIN, MAX);

        Integer[] evenArray = { 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 42 };
        Integer[] oddArray = { 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39, 41 };
        Integer[] squareArray = { 1, 4, 9, 16, 25, 36 };

        Predicate<Integer> evenPredicate = n -> n % 2 == 0;
        Predicate<Integer> oddPredicate = n -> n % 2 != 0;
        Predicate<Integer> squarePredicate = n -> Math.sqrt(n) % 1 == 0;

        rangeIt.setPredicate(evenPredicate);
        Iterator<Integer> evenIt = rangeIt.iterator();
        Integer[] evenActual = collectActualArray(evenIt, evenArray.length);
        assertArrayEquals(evenArray, evenActual);
        assertThrowsExactly(NoSuchElementException.class, evenIt::next);

        rangeIt.setPredicate(oddPredicate);
        Iterator<Integer> oddIt = rangeIt.iterator();
        Integer[] oddActual = collectActualArray(oddIt, oddArray.length);
        assertArrayEquals(oddArray, oddActual);
        assertThrowsExactly(NoSuchElementException.class, oddIt::next);

        rangeIt.setPredicate(squarePredicate);
        Iterator<Integer> squareIt = rangeIt.iterator();
        Integer[] squareActual = collectActualArray(squareIt, squareArray.length);
        assertArrayEquals(squareArray, squareActual);
        assertThrowsExactly(NoSuchElementException.class, squareIt::next);
    }

    private Integer[] collectActualArray(Iterator<Integer> it, int length) {
        Integer[] array = new Integer[length];
        int index = 0;
        while(index < length) {
            array[index++] = it.next();
        }
        return array;
    }
}
