package advent.advent11;

import advent.Loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Advent11 {


    public static final int SEIZE = 10;
    private Octopus[][] map = new Octopus[SEIZE][SEIZE];
    private int flashes = 0;
    private int turnFlashes = 0;


    public void calc() throws IOException {
        InputStream inputStream = Loader.loadFile("ad11.txt");

        fillMap();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            int yPos = 0;
            while ((line = br.readLine()) != null) {
                char[] chars = line.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    map[yPos][i].setValue(Character.getNumericValue(chars[i]));
                }
                yPos++;
            }
        }

        printMap();
        part1(1000);
        System.out.println("result: " + flashes);

    }

    public void part1(int steps) {
        for(int step = 0; step < steps; step++) {
            turnFlashes = 0;

            //increase energy level
            for (int j = 0; j < map.length; j++) {
                for (int i = 0; i < map[j].length; i++) {
                    map[j][i].incValue(1);
                }
            }

            //flash
            for (int j = 0; j < map.length; j++) {
                for (int i = 0; i < map[j].length; i++) {
                    flashNeighbours(i, j);
                }
            }

            //set 0
            for (int j = 0; j < map.length; j++) {
                for (int i = 0; i < map[j].length; i++) {
                    if (map[j][i].getValue() > 9) {
                        map[j][i].setValue(0);
                        map[j][i].setFlashed(false);
                    }
                }
            }

//            printMap();
            if(turnFlashes == SEIZE * SEIZE) {
                System.out.println("allFlashed: " + (step + 1));
                return;
            }
        }
    }

    void flashNeighbours(int xPos, int yPos) {
        Octopus octopus = map[yPos][xPos];
        if (octopus.getValue() > 9 && !octopus.isFlashed()) {
            octopus.setFlashed(true);
            flashes++;
            turnFlashes++;
            flashNeighbour(xPos - 1, yPos - 1);
            flashNeighbour(xPos, yPos - 1);
            flashNeighbour(xPos + 1, yPos - 1);
            flashNeighbour(xPos + 1, yPos);
            flashNeighbour(xPos + 1, yPos + 1);
            flashNeighbour(xPos, yPos + 1);
            flashNeighbour(xPos - 1, yPos + 1);
            flashNeighbour(xPos - 1, yPos);
        }
    }

    void flashNeighbour(int xPos, int yPos) {
        if (xPos < 0 || xPos >= SEIZE || yPos < 0 || yPos >= SEIZE) {
            //do nothing out of bounds
        } else {
            Octopus octopus = map[yPos][xPos];
            octopus.incValue(1);
            flashNeighbours(xPos, yPos);
        }
    }

    public void printMap() {
        for (int j = 0; j < map.length; j++) {
            for (int i = 0; i < map[j].length; i++) {
                System.out.print(map[j][i].getValue());
            }
            System.out.println();
        }
        System.out.println("--- ------- --- flashes: " + flashes);
    }

    public void fillMap() {
        for (int j = 0; j < map.length; j++) {
            for (int i = 0; i < map[j].length; i++) {
                map[j][i] = new Octopus();
            }
        }
    }
}
