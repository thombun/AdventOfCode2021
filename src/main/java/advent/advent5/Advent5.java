package advent.advent5;

import advent.Loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Advent5 {

    private final Integer[][] board = new Integer[1000][1000];

    public void calc() throws IOException {
        InputStream inputStream = Loader.loadFile("ad5.txt");

        initBoard();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split("->");
                drawLine(split);
            }
        }

//        printBoard();
        System.out.println("result is: " + calcResult());
    }

    private void drawLine(String[] split) {
        String[] split1 = split[0].split(",");
        int x1 = Integer.parseInt(split1[0].trim());
        int y1 = Integer.parseInt(split1[1].trim());

        String[] split2 = split[1].split(",");
        int x2 = Integer.parseInt(split2[0].trim());
        int y2 = Integer.parseInt(split2[1].trim());

        if (x1 == x2) {
            int steps = Math.abs(y1 - y2);
            int yDir = y1 < y2 ? 1 : -1;
            for (int i = 0; i <= steps; i++) {
                board[x1][y1 + (yDir * i)] += 1;
            }
        } else if (y1 == y2) {
            int steps = Math.abs(x1 - x2);
            int xDir = x1 < x2 ? 1 : -1;
            for (int i = 0; i <= steps; i++) {
                board[x1 + (xDir * i)][y1] += 1;
            }
        } else if (Math.abs(x1 - x2) == Math.abs(y1 - y2)) {
            int steps = Math.abs(x1 - x2);
            int xDir = x1 < x2 ? 1 : -1;
            int yDir = y1 < y2 ? 1 : -1;

            for (int i = 0; i <= steps; i++) {
                board[x1 + (xDir * i)][y1 + (yDir * i)] += 1;
            }
        }
    }

    private int calcResult() {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[j][i] >= 2) {
                    count++;
                }
            }
        }
        return count;
    }

    public void initBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[j][i] = 0;
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[j][i] == 0) {
                    System.out.print(".");
                } else {
                    System.out.print(board[j][i]);
                }
            }
            System.out.println();
        }
    }
}
