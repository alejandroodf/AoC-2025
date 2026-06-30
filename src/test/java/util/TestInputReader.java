package util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class TestInputReader {
    public static String readInput(int day, char part, String filename) {
        String resourcePath = String.format("/d%02d-%c/%s", day, part, filename);
        try (InputStream is = TestInputReader.class.getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new IOException("Recurso no encontrado: " + resourcePath);
            }
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el recurso: " + resourcePath, e);
        }
    }
}
