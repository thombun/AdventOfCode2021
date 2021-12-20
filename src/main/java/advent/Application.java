package advent;

import advent.advent17.Advent17;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public class Application {

    public static void main(String[] args) throws IOException {
        Instant now = Instant.now();
        Advent17 advent = new Advent17();
        advent.calc();
        System.out.println("took: " + Duration.between(now, Instant.now()).toMillis() + " millis.");

    }
}
