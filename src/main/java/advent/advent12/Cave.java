package advent.advent12;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Cave {
    private String name;
    private List<Cave> caves = new ArrayList<>();
    private boolean isSmall;

    public Cave(String name, boolean isSmall) {
        this.name = name;
        this.isSmall = isSmall;
    }

    public void addCave(Cave cave) {
        if (!caves.contains(cave)) {
            caves.add(cave);
        }
    }
}
