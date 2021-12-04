package advent;

import java.io.InputStream;

public class Loader {

    public static InputStream loadFile(String fileName) {
        return Loader.class.getResourceAsStream("../" +fileName);
    }
}
