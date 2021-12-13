package advent.advent12;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Advent12Test {

    Advent12 advent12 = new Advent12();

    @Test
    public void testLowerCaseCheck() {
        assertTrue(advent12.isLowerCase("a"));
        assertFalse(advent12.isLowerCase("A"));
    }

}