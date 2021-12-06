package advent.advent6;

import advent.Loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Advent6 {

    public void calc() throws IOException {
        InputStream inputStream = Loader.loadFile("ad6.txt");
        List<Long> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                for (String s : split) {
                    list.add(Long.parseLong(s));
                }
            }
        }

        long result = part2(256, list);
        System.out.println("Result: " + result);
    }

    public int part1(int days, List<Integer> list) {
        int currDay = 0;
        int oldSize = list.size();

        List<Integer> newFishes;

        System.out.println("Initial state: " + list);

        while (currDay < days) {
            System.out.println("day: " + currDay + " fishes: " + list.size() + " diff: " + (list.size() - oldSize));
            oldSize = list.size();
            currDay++;
            newFishes = new ArrayList<>();

            for (int i = 0; i < list.size(); i++) {

                if (list.get(i) == 0) {
                    list.set(i, 7);
                    newFishes.add(8);
                }

                list.set(i, list.get(i) - 1);
            }

            list.addAll(newFishes);
        }

        return list.size();
    }

    public Long part2(int days, List<Long> list) {
        int currDay = 0;
        Long[] fishes = new Long[9];

        Arrays.fill(fishes, 0L);

        for (int i = 0; i < list.size(); i++) {
            fishes[list.get(i).intValue()] += 1;
        }

        while (currDay < days) {
            Long newFish = fishes[0];
            currDay++;

            fishes[0] = 0L;
            for (int i = 1; i < fishes.length; i++) {
                fishes[i-1] = fishes[i];
            }

            //spawn new fishes
            fishes[6] += newFish;
            fishes[8] = newFish;
        }

        return Arrays.stream(fishes).sequential().mapToLong(Long::longValue).sum();
    }
}
