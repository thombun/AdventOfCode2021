package advent.Advent8;

import advent.Loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Advent8 {

    Map<Integer, List<Integer>> lengthValues = new HashMap<>();

    public void calc() throws IOException {
        InputStream inputStream = Loader.loadFile("ad8.txt");

        fillMap();
        int result = 0;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split("\\|");
                String[] first = split[0].trim().split("\s");
                String[] second = split[1].trim().split("\s");

                result += part2(first, second);
            }
        }

        System.out.println("Result: " + result);
    }

    private void fillMap() {
        lengthValues.put(2, Collections.singletonList(1));
        lengthValues.put(5, List.of(2, 3, 5));
        lengthValues.put(4, Collections.singletonList(4));
        lengthValues.put(6, List.of(6, 9, 0));
        lengthValues.put(3, Collections.singletonList(7));
        lengthValues.put(7, Collections.singletonList(8));
    }

    private int part2(String[] first, String[] second) {
        String oneCode = null;
        StringBuilder fiveCode = new StringBuilder();

        StringBuilder number = new StringBuilder();
        for (String s : first) {
            if (s.length() == 2) {
                oneCode = s;
            }
        }

        if (oneCode == null) {
            throw new RuntimeException();
        }

        for (String s : first) {
            if (s.length() == 4) {
                for (int i = 0; i < s.length(); i++) {
                    if(!oneCode.contains(String.valueOf(s.charAt(i)))) {
                        fiveCode.append(s.charAt(i));
                    }
                };
            }
        }

        if (fiveCode.length() != 2) {
            throw new RuntimeException();
        }

        for (String s : second) {
            if (lengthValues.containsKey(s.length()) && lengthValues.get(s.length()).size() == 1) {
                number.append(lengthValues.get(s.length()).get(0));
            } else {
                if (s.length() == 5) {
                    if (containsChars(s, oneCode)) {
                        number.append(3);
                    } else if (containsChars(s, fiveCode.toString())) {
                        number.append(5);
                    } else {
                        number.append(2);
                    }
                } else if (s.length() == 6) {
                    if(!containsChars(s, fiveCode.toString())) {
                        number.append(0);
                    } else if (containsChars(s, oneCode)) {
                        number.append(9);
                    } else {
                        number.append(6);
                    }
                }
            }
        }
        return Integer.parseInt(number.toString());
    }

    protected boolean containsChars(String a, String b) {
        boolean containsAll = true;
        for (int i = 0; i < b.length(); i++) {
            containsAll &= a.contains(String.valueOf(b.charAt(i)));
        }

        return containsAll;
    }
}
