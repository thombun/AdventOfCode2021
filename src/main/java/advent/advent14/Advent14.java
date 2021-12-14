package advent.advent14;

import advent.Loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Advent14 {

    String polymer;
    Map<String, PairInserter> pairLookup = new HashMap<>();
    List<Character> chars = new ArrayList<>();


    public void calc() throws IOException {
        InputStream inputStream = Loader.loadFile("ad14.txt");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(" -> ")) {
                    String[] split = line.split(" -> ");

                    String insert = split[0].charAt(0) + split[1] + split[0].charAt(1);
                    String insertA = split[0].charAt(0) + split[1];
                    String insertB = split[1] + split[0].charAt(1);
                    pairLookup.put(split[0], new PairInserter(split[0], insert, insertA, insertB));
                    chars.add(split[1].charAt(0));
                } else if (!line.isEmpty()) {
                    polymer = line;
                }
            }


//            long result = part1(10);
            long result = part2(40);
            System.out.println("result: " + result);
        }
    }

    private int part1(int steps) {
        for (int j = 0; j < steps; j++) {
            StringBuilder newPolymer = new StringBuilder();

            for (int i = 0; i < polymer.length() - 1; i++) {
                String substring = polymer.substring(i, i + 2);
                if (pairLookup.containsKey(substring)) {
                    if (newPolymer.length() > 0) {
                        newPolymer.deleteCharAt(newPolymer.length() - 1);
                    }
                    newPolymer.append(pairLookup.get(substring).getInsert());
                } else {
                    newPolymer.append(polymer.charAt(i));
                }
            }

            polymer = newPolymer.toString();
            result();
        }

        return result();
    }

    private long part2(int steps) {
        Map<String, Long> poly = new HashMap<>();

        for (int i = 0; i < polymer.length() - 1; i++) {
            String substring = polymer.substring(i, i + 2);
            if (poly.containsKey(substring)) {
                poly.put(substring, poly.get(substring) + 1);
            } else {
                poly.put(substring, 1L);
            }
        }

        for (int i = 0; i < steps; i++) {
            Map<String, Long> poly1 = new HashMap<>();
            List<String> keys = new ArrayList<>(poly.keySet());

            for (String s : keys) {
                PairInserter pairInserter = pairLookup.get(s);

                long num = poly.get(s);
                if (num <= 0) {
                    poly1.remove(s);
                    continue;
                }

                if (poly1.containsKey(pairInserter.getInsertA())) {
                    poly1.put(pairInserter.getInsertA(), poly1.get(pairInserter.getInsertA()) + num);
                } else {
                    poly1.put(pairInserter.getInsertA(), num);
                }

                if (poly1.containsKey(pairInserter.getInsertB())) {
                    poly1.put(pairInserter.getInsertB(), poly1.get(pairInserter.getInsertB()) + num);
                } else {
                    poly1.put(pairInserter.getInsertB(), num);
                }
            }

            poly = poly1;
        }

        return result2(poly);
    }

    public Long result2(Map<String, Long> poly) {
        List<String> keys = new ArrayList<>(poly.keySet());
        Map<Character, Long> resultMap = new HashMap<>();

        for (Character aChar : chars) {
            long count = 0;
            for (String key : keys) {
                if (key.startsWith(aChar.toString())) {
                    if (poly.get(key) > 0) {
                        count += poly.get(key);
                    }
                }
                if (key.endsWith(aChar.toString())) {
                    if (poly.get(key) > 0) {
                        count += poly.get(key);
                    }
                }
            }

            count++;
            resultMap.put(aChar, count/2);
        }

        List<Long> collect = resultMap.values().stream()
                                   .sorted(Comparator.naturalOrder())
                                   .collect(Collectors.toList());

        return collect.get(collect.size() - 1) - collect.get(0);
    }

    public int result() {
        Map<Character, Integer> map = new HashMap<>();

        for (char c : polymer.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }

        List<Integer> collect = map.values().stream()
                                   .sorted(Comparator.naturalOrder())
                                   .collect(Collectors.toList());

        return collect.get(collect.size() - 1) - collect.get(0);
    }
}
