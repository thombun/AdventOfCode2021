import java.io.InputStream;

public class Loader {

    static InputStream loadFile(String fileName) {
        return Loader.class.getResourceAsStream(fileName);
    }
}
