package advent.advent10;

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
import java.util.Stack;

public class Advent10 {

    private final Map<Character, Character> openers = new HashMap<>();
    private final Map<Character, Character> closers = new HashMap<>();
    private final List<Long> scores = new ArrayList<>();

    int count = 1;

    public void calc() throws IOException {
        InputStream inputStream = Loader.loadFile("ad10.txt");

        int result = 0;
        fillmaps();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                long score = part1(line);
                if (score != -1) {
                    scores.add(score);
                }
                count++;
            }
        }

        scores.sort(Comparator.naturalOrder());
        System.out.println("Result: " + scores.get(scores.size() / 2));
    }


    public long part1(String line) {
        Stack<Character> stack = new Stack<>();
        for (char c : line.toCharArray()) {
            if (openers.containsKey(c)) {
                stack.push(c);
            } else {
                Character pop = stack.pop();
                if (pop != closers.get(c)) {
                    System.out.println(" " + count + " Expected " + openers.get(pop) + " but found " + c);
//                    return getErrorScore(c);
                    return -1;
                }
            }
        }

        //line incomplete add missing characters
        long score = 0;
        while(stack.size() > 0) {
            score = getPart2score(openers.get(stack.pop()), score);
        }
        return score;
    }

    private int getErrorScore(Character c) {
        return switch (c) {
            case ')' -> 3;
            case ']' -> 57;
            case '}' -> 1197;
            case '>' -> 25137;
            default -> throw new RuntimeException("No errorScore available for Character: " + c + ".");
        };
    }

    private long getPart2score(Character c, long score) {
        score *= 5;

        return switch (c) {
            case ')' -> score + 1;
            case ']' -> score + 2;
            case '}' -> score + 3;
            case '>' -> score + 4;
            default -> throw new RuntimeException("No errorScore available for Character: " + c + ".");
        };
    }

    private void fillmaps() {
        openers.put('(', ')');
        openers.put('<', '>');
        openers.put('[', ']');
        openers.put('{', '}');

        closers.put(')', '(');
        closers.put('>', '<');
        closers.put(']', '[');
        closers.put('}', '{');
    }
}
