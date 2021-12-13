package advent.advent12;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static advent.advent12.Advent12.END;
import static advent.advent12.Advent12.START;


@Data
@NoArgsConstructor
public class Runner {

    private List<String> paths = new ArrayList<>();

    boolean isLowerCase(String s) {
        return s.equals(s.toLowerCase());
    }

    public List<String> visitCaves1(String path, Cave cave) {
        path += cave.getName() + ",";
        for (int i = 0; i < cave.getCaves().size(); i++) {
            Cave nextCave = cave.getCaves().get(i);

            if (cave.getName().equals(END)) {
                paths.add(path);
            } else if (nextCave.isSmall() && path.contains(nextCave.getName()) || nextCave.getName().equals(START)) {
            } else {
                visitCaves1(path, nextCave);
            }
        }

        return paths;
    }

    public List<String> visitCaves(String path, Cave cave) {
        path += cave.getName() + ",";

        for (int i = 0; i < cave.getCaves().size(); i++) {
            Cave nextCave = cave.getCaves().get(i);

            if (cave.getName().equals(END)) {
                paths.add(path);
            } else if (visitNextCave(path, nextCave)) {
                visitCaves(path, nextCave);
            } else {
//                System.out.println(path + " : " + nextCave.getName());
            }
        }

        return paths;

    }

    public boolean visitNextCave(String path, Cave cave) {
        if (cave.getName().equals(START)) {
            return false;
        }

        if (path.contains("end")) {
            return false;
        }

        if (!cave.isSmall()) {
            return true;
        }

//        if (isLowerCase(cave.getName()) && path.contains(cave.getName())) {
//            return false;
//        }

        List<String> split = List.of(path.split(","));
        //check for 2 visited small caves
        String doubleVisitedSmallCave = null;
        boolean containsCave = false;
        Set<String> set = new HashSet<>();
        for (String s : split) {
            if (set.contains(s) && isLowerCase(s)) {
                doubleVisitedSmallCave = s;
            }
            if (s.equals(cave.getName()) && isLowerCase(s)) {
                containsCave = true;
            }
            set.add(s);

        }
        if (cave.getName().equals(doubleVisitedSmallCave)) {
            return false;
        }
        if (doubleVisitedSmallCave != null && containsCave) {
            return false;
        }

        return true;
    }

    public String toStringPath(List<String> path) {
        StringBuilder builder = new StringBuilder();
        for (String s : path) {
            builder.append(s).append(',');
        }
        return builder.toString();
    }
}
