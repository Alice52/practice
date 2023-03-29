package io.github.alice52.test.util;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * @author T04856 <br>
 * @create 2023-03-29 1:55 PM <br>
 * @project project-cloud-custom <br>
 */
public class ResourceHelper {
    @SneakyThrows
    public static String getResourceAsString(String fname) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        try (InputStream is = classLoader.getResourceAsStream(fname)) {
            if (is == null) {
                return null;
            }
            try (InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader reader = new BufferedReader(isr)) {
                return reader.lines().collect(Collectors.joining(System.lineSeparator()));
            }
        }
    }
}
