package advent.advent15;

import advent.Loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Advent15 {

    public static final String FILE = "ad15.txt";

    public static int WIDTH = 10;
    public static int STEP = 10;
    public static int HEIGHT = 10;
    private Integer[][] map;
    private Integer[][] mapLookup;

    public void calc() throws IOException {
        InputStream inputStream = Loader.loadFile(FILE);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            int height = 0;
            int width = 0;
            while ((line = br.readLine()) != null) {
                height = line.length();
                width++;
            }

            STEP = width;
            WIDTH = width * 5;
            HEIGHT = height * 5;
            map = new Integer[HEIGHT][WIDTH];
            mapLookup = new Integer[HEIGHT][WIDTH];
        }

        inputStream = Loader.loadFile(FILE);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            int yPos = 0;
            while ((line = br.readLine()) != null) {
                char[] chars = line.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    map[yPos][i] = Character.getNumericValue(chars[i]);
                    mapLookup[yPos][i] = Integer.MAX_VALUE;
                }
                yPos++;
            }
        }

        int path = part1();
        System.out.println("result: " + path);
    }

    private int part1() {
        mapLookup[0][0] = 0;

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                int v = mapLookup[y][x];

                setIfSmaller(y, x, v);

                if (x < (WIDTH) - 1) {//right
                    setIfSmaller(y, x + 1, v);

                }

                if (y < (HEIGHT) - 1) {//down
                    setIfSmaller(y + 1, x, v);
                }

                if (y > 0) {//up
                    if (setIfSmaller(y - 1, x, v)) {
                        if (x > 0) {
                            x--;
                        }
                        y--;
                    }
                }

                if (x > 0) {//left
                    if (setIfSmaller(y, x - 1, v)) {
                        x--;
                        if (y > 0) {
                            y--;
                        }
                    }
                }
            }
        }

        return mapLookup[HEIGHT - 1][WIDTH - 1];
    }

    public boolean setIfSmaller(int y, int x, int v) {
        if (mapLookup[y][x] == null) {
            mapLookup[y][x] = Integer.MAX_VALUE;
        }

        int value = getValue(y, x);

        if (value + v < mapLookup[y][x]) {
            mapLookup[y][x] = value + v;
            return true;
        }
        return false;
    }

    public int getValue(int y, int x) {
        int riskLevel = y / STEP + x / STEP;
        int i = map[y % STEP][x % STEP] + riskLevel;

        int j = i;
        if (j > 9) {
            j = i % 9;
        }

        return j;
    }

    public void printMap(Integer[][] map) {
        for (int j = 0; j < map.length; j++) {
            for (int i = 0; i < map[j].length; i++) {
                if (map[j][i] == Integer.MAX_VALUE) {
                    System.out.print("     ");
                } else {
                    System.out.printf("%04d", map[j][i]);
                    System.out.print(" ");
                }

            }
            System.out.println();
        }
        System.out.println();
    }
}
