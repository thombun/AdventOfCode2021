package advent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Advent2 {

    List<String> moves = new ArrayList<>();

    public void calc() throws IOException {
        InputStream inputStream = Loader.loadFile("advent2.txt");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                moves.add(line);
            }
        }

        part2();
    }

    public void part2() {
        int aim = 0;
        int posX = 0;
        int posY = 0;

        for(int i = 0; i< moves.size(); i++) {
            String s = moves.get(i);

            if(s.contains("down")) {
                aim += cutInt(s);
            } else if (s.contains("up")) {
                aim -= cutInt(s);
            } else if(s.contains("forward")) {
                int speed = cutInt(s);
                posX += speed;
                posY += speed * aim;
            }
        }

        System.out.println("posX: " + posX + " posY: " + posY + " mult: "+ posX * posY);
    }

    public void part1() {
        Integer horizontal = moves
                .stream()
                .filter(e -> e.contains("forward"))
                .map(e -> Integer.parseInt(e.substring(e.indexOf(' ') + 1)))
                .mapToInt(Integer::intValue)
                .sum();

        Integer down = moves
                .stream()
                .filter(e -> e.contains("down"))
                .map(e -> Integer.parseInt(e.substring(e.indexOf(' ') + 1)))
                .mapToInt(Integer::intValue)
                .sum();

        Integer up = moves
                .stream()
                .filter(e -> e.contains("up"))
                .map(e -> Integer.parseInt(e.substring(e.indexOf(' ') + 1)))
                .mapToInt(Integer::intValue)
                .sum();


        System.out.println("moved: horizontal: " + horizontal + " vertical: " + (down - up) + " mult: " + horizontal * (down - up));
    }

    private int cutInt(String s) {
        return Integer.parseInt(s.substring(s.indexOf(' ') + 1));
    }
}
