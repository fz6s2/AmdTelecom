package org.amd.task03;

import org.amd.task03.Task03;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task03Test {
    @ParameterizedTest(name = "Task03 test: sum of digits {0}")
    @MethodSource("resultSet")
    void remakeWords(String text, String expected) {
        assertEquals(Task03.doRemake(text), expected);
    }

    static Stream<Arguments> resultSet() {
        return Stream.of(
                Arguments.of("Cats are great pets.", "Atscay areway reatgay etspay."),
                Arguments.of("Tom got a small piece of pie.", "Omtay otgay away mallsay iecepay ofway iepay."),
                Arguments.of("He told us a very exciting tale.", "Ehay oldtay usway away eryvay excitingway aletay."),
                Arguments.of("My name is Tom, and him not", "Ymay amenay isway Omtay, andway imhay otnay")
        );
    }
}
