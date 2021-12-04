package advent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Advent1 {

    static List<Integer> measures = new ArrayList<>();

    public void calc() throws IOException {
        InputStream inputStream = Loader.loadFile("measure.txt");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                measures.add(Integer.parseInt(line));
            }
        }

        int last = -1;
        int count = -1;

        for (int i = 2; i < measures.size(); i++) {
            int curr = measures.get(i) + measures.get(i - 1) + measures.get(i - 2);

            if (curr > last) {
                count++;
            }
            last = curr;
        }
        System.out.println(count);
    }
}
