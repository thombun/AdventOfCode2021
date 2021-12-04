package advent.advent4;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardItem {
    private int number;
    private boolean marked;
}
