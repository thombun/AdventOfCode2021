package advent.advent6;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Advent6Test {

    @Test
    public void testSamplePart1() {
        Advent6 advent6 = new Advent6();
        assertEquals(26, advent6.part1(18, new ArrayList<>(List.of(3, 4, 3, 1, 2))));
        assertEquals(5934, advent6.part1(80, new ArrayList<>(List.of(3, 4, 3, 1, 2))));
    }

    @Test
    public void testSamplePart2() {
        Advent6 advent6 = new Advent6();
        assertEquals(26, advent6.part2(18, new ArrayList<>(List.of(3L, 4L, 3L, 1L, 2L))));
        assertEquals(5934, advent6.part2(80, new ArrayList<>(List.of(3L, 4L, 3L, 1L, 2L))));
    }

}