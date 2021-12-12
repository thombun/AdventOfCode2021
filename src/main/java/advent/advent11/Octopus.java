package advent.advent11;

import lombok.Data;

@Data
public class Octopus {
    private boolean flashed = false;
    private int value = 0;

    public void incValue(int amount) {
        value += amount;
    }
}
