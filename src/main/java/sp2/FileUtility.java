package sp2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileUtility {
    public static String[] toStringArray(String filepath, String delimiter) throws IOException {
        return Files.lines(Paths.get(filepath))
        .flatMap(line -> Stream.of(line.split(delimiter)))
        .map(seq -> seq.replace("-", " ")) 
        .map(seq -> seq.replaceAll("[^A-Za-z]",""))
        .filter(seq -> !seq.isEmpty())
        .map(seq -> seq.toLowerCase())
        .toArray(String[]::new);
    }
}