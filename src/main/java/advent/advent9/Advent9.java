package advent.advent9;

import advent.Loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Advent9 {

    public static final String FILE = "ad9.txt";
    private Integer[][] heightMap = null;

    public void calc() throws IOException {
        InputStream inputStream = Loader.loadFile(FILE);
        int height = 0;
        int width = 0;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                height++;
                width = line.length();
            }
        }

        heightMap = new Integer[height][width];

        inputStream = Loader.loadFile(FILE);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            int yPos = 0;
            while ((line = br.readLine()) != null) {
                char[] chars = line.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    heightMap[yPos][i] = Character.getNumericValue(chars[i]);
                }
                yPos++;
            }
        }

//        printMap();
        int resultOne = part1();
        List<Integer> result = part2();
        result.sort(Comparator.reverseOrder());
        int sum = result.get(0) * result.get(1) * result.get(2);
        System.out.println("result1: " + resultOne);
        System.out.println("result2: " + sum);

    }

    public int part1() {
        int sumLowPoints = 0;
        for (int j = 0; j < heightMap.length; j++) {
            for (int i = 0; i < heightMap[j].length; i++) {
                if (isLowPoint(i, j)) {
                    sumLowPoints += (1 + heightMap[j][i]);
                }
            }
        }

        return sumLowPoints;
    }

    public List<Integer> part2() {
        List<Integer> results = new ArrayList<>();

        for (int j = 0; j < heightMap.length; j++) {
            for (int i = 0; i < heightMap[j].length; i++) {
                results.add(checkForBasin(i, j));
            }
        }
        return results;
    }

    public int checkForBasin(int xPos, int yPos) {
        int basinSize = 0;
        int height = heightAt(xPos, yPos);
        if (height == 9) {
            return 0;
        }
        heightMap[yPos][xPos] = 9;
        basinSize++;

        //go up
        basinSize += checkForBasin(xPos, yPos - 1);
        //go down
        basinSize += checkForBasin(xPos, yPos + 1);
        //go left
        basinSize += checkForBasin(xPos - 1, yPos);
        //go right
        basinSize += checkForBasin(xPos + 1, yPos);

        return basinSize;
    }

    int heightAt(int xPos, int yPos) {
        if (xPos < 0 || xPos >= heightMap[0].length || yPos < 0 || yPos >= heightMap.length) {
            return 9;
        } else {
            return heightMap[yPos][xPos];
        }
    }

    boolean isLowPoint(int xPos, int yPos) {
        int point = heightMap[yPos][xPos];

        List<Integer> neighbours = new ArrayList<>();
        //check top
        neighbours.add(heightAt(xPos, yPos - 1));
        //check bottom
        neighbours.add(heightAt(xPos, yPos + 1));
        //check left
        neighbours.add(heightAt(xPos - 1, yPos));
        //check right
        neighbours.add(heightAt(xPos + 1, yPos));

        for (Integer neighbour : neighbours) {
            if (neighbour <= point) {
                return false;
            }
        }

        return true;
    }

    public void printMap() {
        for (int j = 0; j < heightMap.length; j++) {
            for (int i = 0; i < heightMap[j].length; i++) {
                System.out.print(heightMap[j][i]);
            }
            System.out.println();
        }
    }
}
