package advent.advent7;

import advent.Loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Advent7 {

    public void calc() throws IOException {
        InputStream inputStream = Loader.loadFile("ad7.txt");
        List<Integer> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                for (String s : split) {
                    list.add(Integer.parseInt(s));
                }
            }
        }

        int result = part2(list);
        System.out.println("Result: " + result);
    }

    int part1(List<Integer> list) {
        List<Integer> results = new ArrayList<>();
        int max = list.stream().max(Integer::compare).get();

        int sum = 0;
        for (int i = 0; i < max; i++) {
            for (Integer pos : list) {
                sum += Math.abs(pos - i);
            }
            results.add(sum);
            sum = 0;
        }

        return results.stream().min(Integer::compare).get();
    }

    int part2(List<Integer> list) {
        List<Integer> results = new ArrayList<>();
        int max = list.stream().max(Integer::compare).get();

        int sum = 0;
        for (int i = 0; i < max; i++) {
            for (Integer pos : list) {
                sum += calcSum(Math.abs(pos - i));
            }
            results.add(sum);
            sum = 0;
        }

        return results.stream().min(Integer::compare).get();
    }

    int calcSum(int sum) {
        if(sum <= 1) {
            return sum;
        } else {
            return sum + calcSum(sum -1);
        }
    }
}
