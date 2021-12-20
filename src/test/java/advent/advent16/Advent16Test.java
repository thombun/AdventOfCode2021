package advent.advent16;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Advent16Test {

    Advent16 advent16 = new Advent16();

    @Test
    public void testLiteral() {
//        assertEquals(2021, advent16.getLiteral("101111111000101000"));
//        assertEquals(10, advent16.getLiteral("11010001010"));
//        assertEquals(20, advent16.getLiteral("01010010001001000000000"));
        assertEquals(1, advent16.getLiteral("01010000001", 11));
        assertEquals(2, advent16.getLiteral("10010000010", 11));
        assertEquals(3, advent16.getLiteral("00110000011", 11));
    }

}