package org.amd.task02;

import org.amd.task02.Task02;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task02Test {

    @ParameterizedTest(name = "Task02 test: sum of digits {0} = {1}")
    @CsvSource(value = {
            "10, 1",
            "38, 2",
            "785, 2",
            "99892, 1"
    })
    void sumOfDigits(int number, int expected) {
        assertEquals(Task02.digitSum(number), expected);
    }
}
