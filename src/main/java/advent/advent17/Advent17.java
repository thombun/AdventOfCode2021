package advent.advent17;

import advent.Loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.Instant;

public class Advent17 {

    public static final String FILE = "ad17.txt";

    int maxY = 0;
    int hitCount = 0;

    int velX;
    int velY;
    int x = 0;
    int y = 0;

    int asx;
    int asy;
    int aex;
    int aey;

    public void calc() throws IOException {
        Instant now = Instant.now();
        InputStream inputStream = Loader.loadFile(FILE);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.substring("target area: ".length());
                String[] split = line.split(", ");

                String[] split1 = split[0].substring(2).split("\\.\\.");
                asx = Integer.parseInt(split1[0]);
                aex = Integer.parseInt(split1[1]);

                String[] split2 = split[1].substring(2).split("\\.\\.");
                asy = Integer.parseInt(split2[0]);
                aey = Integer.parseInt(split2[1]);
                System.out.println();
            }
        }

        part1();
        Instant now1 = Instant.now();
        System.out.println("result: " + hitCount + " took: " + Duration.between(now, now1).toMillis());
    }

    private void part1() {
        x = 0;
        y = 0;

        for (int i = 0; i < 500; i++) {
            for (int j = -100; j < 500; j++) {
                shoot(i, j);
            }

        }
    }

    private void shoot(int x, int y) {
        velX = x;
        velY = y;
        this.x = 0;
        this.y = 0;
        int tmpMaxY = 0;
        for (int i = 0; i < 1000; i++) {
            move();
            tmpMaxY = Math.max(tmpMaxY, this.y);
            boolean isHit = isHit();
            if (isHit) {
                maxY = Math.max(maxY, tmpMaxY);
                System.out.println(this.x + " " + this.y + " hit: " + isHit + " " + maxY);
                hitCount++;
                break;
            }
        }
    }

    private boolean isHit() {
        return (x >= asx && x <= aex && y >= asy && y <= aey);
    }

    private void move() {
        x += velX;
        y += velY;

        if (velX > 0) {
            velX--;
        } else if (velX < 0) {
            velX++;
        }

        velY--;
    }
}
