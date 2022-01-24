package org.amd.task01;

import org.amd.task01.Task01;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task01Test {
    @ParameterizedTest(name = "Task01 test: find 7 in array with 7 {0}")
    @MethodSource("arraysWithSeven")
    void arrayHasSeven(int [] array) {
        assertEquals("Found", Task01.findSeven(array));
    }

    @ParameterizedTest(name = "Task01 test: find 7 in array without 7 {0}")
    @MethodSource("arraysWithoutSeven")
    void arrayDoesntHaveSeven(int [] array) {
        assertEquals("there is no 7 in the array", Task01.findSeven(array));
    }

    static Stream<int []> arraysWithSeven() {
        return Stream.of(
                new int[] {1, 2, 3, 4, 5, 6, 7},
                new int[] {2, 55, 60, 97, 86}
        );
    }

    static Stream<int []> arraysWithoutSeven() {
        return Stream.of(
                new int[] {8, 6, 33, 100}
        );
    }
}