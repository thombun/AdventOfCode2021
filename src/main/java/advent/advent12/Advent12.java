package advent.advent12;

import advent.Loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Advent12 {

    static final String START = "start";
    static final String END = "end";

    Map<String, Cave> caves = new HashMap<>();

    public void calc() throws IOException {
        InputStream inputStream = Loader.loadFile("ad12.txt");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split("-");
                String name = split[0];
                String secondName = split[1];
                if (!caves.containsKey(name)) {
                    caves.put(name, new Cave(name, isLowerCase(name)));
                }
                if (!caves.containsKey(secondName)) {
                    Cave cave = new Cave(secondName, isLowerCase(secondName));
                    caves.put(secondName, cave);
                }
                caves.get(name).addCave(caves.get(secondName));
                caves.get(secondName).addCave(caves.get(name));
            }
        }

        List<String> strings = part1().stream()
                                      .distinct()
                                      .collect(Collectors.toList());
//        List<String> strings = part1();
        System.out.println("result: " + strings.size());
    }

    public List<String> part1() {
        Runner runner = new Runner();
        Cave startCave = caves.get(START);
        return runner.visitCaves("", startCave);
    }

    boolean isLowerCase(String s) {
        return s.equals(s.toLowerCase());
    }
}
