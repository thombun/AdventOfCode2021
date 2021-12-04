package advent.advent4;

import lombok.Data;


@Data
public class BingoBoard {
    private BoardItem[][] board = new BoardItem[5][5];
    private boolean won = false;
    private int winNumber;

    public void addLine(String line, int number) {
        String[] s = line.trim().split("\\s+");
        for (int i = 0; i < s.length; i++) {
            if (s[i].isBlank()) {
                continue;
            }
            board[number][i] = new BoardItem(Integer.parseInt(s[i]), false);
        }
    }

    public void markNumber(int number) {
        for (BoardItem[] boardItems : board) {
            for (BoardItem boardItem : boardItems) {
                if (boardItem.getNumber() == number) {
                    boardItem.setMarked(true);
                }
            }
        }
    }

    public boolean isBingo() {
        for (int i = 0; i < 5; i++) {
           if(isLineComplete(i) || isColumnComplete(i)) {
               return true;
           }
        }

        return false;
    }

    public boolean isLineComplete(int number) {
        boolean complete = true;
        for (int i = 0; i < 5; i++) {
            complete &= board[number][i].isMarked();
        }

        return complete;
    }

    public boolean isColumnComplete(int number) {
        boolean complete = true;
        for (int i = 0; i < 5; i++) {
            complete &= board[i][number].isMarked();
        }

        return complete;
    }

    public int value() {
        int sum = 0;
        for (BoardItem[] boardItems : board) {
            for (BoardItem boardItem : boardItems) {
                if(!boardItem.isMarked()) {
                    sum += boardItem.getNumber();
                }
            }
        }

        return sum;
    }
}
