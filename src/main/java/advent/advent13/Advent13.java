package advent.advent13;

import advent.Loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Advent13 {

    public static final String FILE = "ad13.txt";
    public static int WIDTH = 11;
    public static int HEIGHT = 15;
    private Character[][] map;
    private List<Fold> folds = new ArrayList<>();

    public void calc() throws IOException {
        InputStream inputStream = Loader.loadFile(FILE);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            int height = 0;
            int width = 0;
            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    break;
                }

                if (line.contains(",")) {
                    String[] split = line.split(",");
                    width = Math.max(width, Integer.parseInt(split[0]));
                    height = Math.max(height, Integer.parseInt(split[1]));
                }
            }

            WIDTH = width + 1;
            HEIGHT = height + 1;
            map = new Character[HEIGHT][WIDTH];
        }

        fillMap();

        inputStream = Loader.loadFile(FILE);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                }

                if (line.contains("fold along")) {
                    line = line.substring(line.indexOf('=') - 1);
                    String[] split = line.split("=");
                    folds.add(new Fold(split[0], Integer.parseInt(split[1])));
                } else {
                    String[] split = line.split(",");
                    map[Integer.parseInt(split[1])][Integer.parseInt(split[0])] = '#';
                }
            }
        }

//        printMap();
        int count = part1();
        printMap();
        System.out.println("result: " + count);

    }

    private int part1() {
        //fold up
        for (Fold fold : folds) {
            if (fold.getDimension().equals("x")) {
                foldX(fold);
            } else {
                foldY(fold);
            }
            //part 1
//            break;
        }

        return count();
    }

    private void foldY(Fold fold) {
        int axis = fold.getAxis();
        for (int y = 0; y < axis; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if (map[y][x] != '#') {
                    int pos = (axis - y) + axis;
                    if (pos < HEIGHT) {
                        map[y][x] = map[pos][x];
                    }
                }
            }
        }

        for (int y = axis; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                map[y][x] = '*';
            }
        }
        HEIGHT = axis;
        copyArray();
    }

    private void foldX(Fold fold) {
        int axis = fold.getAxis();
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < axis; x++) {
                if (map[y][x] != '#') {
                    int pos = (axis - x) + axis;
                    if (pos < WIDTH) {
                        map[y][x] = map[y][pos];
                    }
                }
            }
        }

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = axis; x < WIDTH; x++) {
                map[y][x] = '*';
            }
        }
        WIDTH = axis;
        copyArray();
    }

    public void copyArray() {
        Character[][] newMap = new Character[HEIGHT][WIDTH];

        for (int j = 0; j < HEIGHT; j++) {
            for (int i = 0; i < WIDTH; i++) {
                newMap[j][i] = map[j][i];
            }
        }
        map = newMap;
    }

    public int count() {
        int count = 0;
        for (int j = 0; j < map.length; j++) {
            for (int i = 0; i < map[j].length; i++) {
                if (map[j][i] == '#') {
                    count++;
                }
            }
        }
        return count;
    }

    public void printMap() {
        for (int j = 0; j < map.length; j++) {
            for (int i = 0; i < map[j].length; i++) {
                System.out.print(map[j][i]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public void fillMap() {
        for (int j = 0; j < map.length; j++) {
            for (int i = 0; i < map[j].length; i++) {
                map[j][i] = '.';
            }
        }
    }
}
