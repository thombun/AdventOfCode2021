import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Advent3 {

    List<String> moves = new ArrayList<>();

    public void calc() throws IOException {
        InputStream inputStream = Loader.loadFile("ad3.txt");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                moves.add(line);
            }
        }

        part2(moves);
    }

    Integer part2(List<String> moves) {
        Integer oxygen = calculateLifeSupportRating(new ArrayList<>(moves), '1', '0', true);
        Integer co2 = calculateLifeSupportRating(new ArrayList<>(moves), '0', '1', false);
        System.out.println("oxygen: " + oxygen + " co2: " + co2);
        System.out.println("result: " + oxygen * co2);

        return oxygen * co2;
    }

    Integer calculateLifeSupportRating(List<String> moves, Character winner, Character loser, boolean higherWins) {
        for(int i = 0; i < moves.get(0).length(); i++) {
            if(countColumnMoreWinner(i, moves, winner, higherWins)) {
                for(int j = 0; j < moves.size(); j++) {
                    if(moves.get(j).charAt(i) != winner) {
                        moves.remove(j);
                        j--;
                    }
                }
            } else {
                for(int j = 0; j < moves.size(); j++) {
                    if (moves.get(j).charAt(i) != loser) {
                        moves.remove(j);
                        j--;
                    }
                }
            }

            if(moves.size() == 1) {
                System.out.println("only one entry left: " + moves.get(0));
                return Integer.parseInt(moves.get(0), 2);
            }
        }

        return -1;
    }

    Integer part1(List<String> moves) {
        StringBuilder gamma = new StringBuilder();
        StringBuilder epsilon = new StringBuilder();

        for(int i = 0; i < moves.get(0).length(); i++) {
            if(countColumnMoreWinner(i, moves, '1',true)) {
                gamma.append('1');
                epsilon.append('0');
            } else {
                gamma.append('0');
                epsilon.append('1');
            }
        }

        System.out.println("gamma: " + Integer.parseInt(gamma.toString(), 2));
        System.out.println("epsilon: " + Integer.parseInt(epsilon.toString(), 2));
        int result = Integer.parseInt(gamma.toString(), 2) * Integer.parseInt(epsilon.toString(), 2);
        System.out.println("result: " + result);
        return result;
    }

    boolean countColumnMoreWinner(int column, List<String> moves, Character winnerChar, boolean higherWins) {
        int winner = 0;
        int loser = 0;

        for(int i = 0; i< moves.size(); i++) {
            if(moves.get(i).charAt(column) == winnerChar) {
                winner++;
            } else {
                loser++;
            }
        }

        if(winner != loser) {
            if(higherWins) {
                return winner > loser;
            } else {
                return winner < loser;
            }
        } else {
            return true;
        }
    }
}
