package advent;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Advent3Test {

    Advent3 advent = new Advent3();

    @Test
    public void testCountColumnMoreWinner() {
        List<String> list = List.of("0", "1", "1");

        assertTrue(advent.countColumnMoreWinner(0, list, '1', true));
        assertFalse(advent.countColumnMoreWinner(0, list, '0', true));
        assertFalse(advent.countColumnMoreWinner(0, list, '1', false));
        assertTrue(advent.countColumnMoreWinner(0, list, '0', false));
    }

    @Test
    public void testPart1Sample() {
        List<String> list =
                List.of("00100", "11110", "10110", "10111", "10101", "01111", "00111", "11100", "10000", "11001",
                        "00010", "01010");

        assertEquals(198, advent.part1(list));
    }

    @Test
    public void testPart2Sample() {
        List<String> list =
                List.of("00100", "11110", "10110", "10111", "10101", "01111", "00111", "11100", "10000", "11001",
                        "00010", "01010");

        assertEquals(230, advent.part2(list));
    }
}