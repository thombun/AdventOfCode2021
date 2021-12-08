package advent.Advent8;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Advent8Test {

    private final Advent8 advent8 = new Advent8();

    @ParameterizedTest
    @MethodSource("provideSamples")
    public void testContainsAll(String a, String b, boolean result) {
        assertEquals(result, advent8.containsChars(a, b));
    }

    private static Stream<Arguments> provideSamples() {
        return Stream.of(
                Arguments.of("abcd", "ac", true),
                Arguments.of("abcd", "ad", true),
                Arguments.of("ab", "ab", true),
                Arguments.of("bcd", "ac", false),
                Arguments.of("bcad", "af", false)
        );
    }
}