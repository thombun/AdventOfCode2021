package advent.advent4;

import advent.Loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Advent4 {

    List<Integer> numbers = new ArrayList<>();
    List<BingoBoard> boards = new ArrayList<>();
    List<BingoBoard> winHistory = new ArrayList<>();

    public void calc() throws IOException {
        InputStream inputStream = Loader.loadFile("ad4num.txt");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                for (String s : split) {
                    numbers.add(Integer.parseInt(s));
                }

            }
        }

        inputStream = Loader.loadFile("ad4boards.txt");
        BingoBoard bingoBoard = new BingoBoard();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            int count = 0;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    count = 0;
                    boards.add(bingoBoard);
                    bingoBoard = new BingoBoard();
                    continue;
                } else {
                    bingoBoard.addLine(line, count);
                    count++;
                }

            }
            boards.add(bingoBoard);
        }

        for (Integer number : numbers) {
            for (BingoBoard board : boards) {
                if (!board.isWon()) {
                    board.markNumber(number);
                }
            }

            for (BingoBoard board : boards) {
                if (board.isBingo()) {
                    if (!board.isWon()) {
                        board.setWon(true);
                        board.setWinNumber(number);
                        winHistory.add(board);
                    }
                }
            }

        }

        BingoBoard board = winHistory.get(winHistory.size() - 1);
        System.out.println("value: " + board.value() + " number: " + board.getWinNumber() + " score: "
                + board.value() * board.getWinNumber());
    }
}
