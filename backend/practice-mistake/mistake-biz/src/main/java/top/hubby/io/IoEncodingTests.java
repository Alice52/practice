package top.hubby.io;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author asd <br>
 * @create 2021-10-29 9:46 AM <br>
 * @project swagger-3 <br>
 */
@Slf4j
public class IoEncodingTests {

    @Test
    public void init() throws IOException {
        Files.deleteIfExists(Paths.get("hello.txt"));
        Files.write(Paths.get("hello.txt"), "你好hi".getBytes(Charset.forName("GBK")));
        log.info(
                "bytes:{}",
                Hex.encodeHexString(Files.readAllBytes(Paths.get("hello.txt"))).toUpperCase());
    }

    @Test
    public void wrong() throws IOException {
        log.info("charset: {}", Charset.defaultCharset());

        char[] chars = new char[10];
        String content = "";
        try (FileReader fileReader = new FileReader("hello.txt")) {
            int count;
            while ((count = fileReader.read(chars)) != -1) {
                content += new String(chars, 0, count);
            }
        }
        log.info("result:{}", content);

        Files.write(Paths.get("hello2.txt"), "你好hi".getBytes(Charsets.UTF_8));
        log.info(
                "bytes:{}",
                Hex.encodeHexString(Files.readAllBytes(Paths.get("hello2.txt"))).toUpperCase());
    }

    @Test
    public void right1() throws IOException {

        char[] chars = new char[10];
        String content = "";
        try (FileInputStream fileInputStream = new FileInputStream("hello.txt");
                InputStreamReader inputStreamReader =
                        new InputStreamReader(fileInputStream, Charset.forName("GBK"))) {
            int count;
            while ((count = inputStreamReader.read(chars)) != -1) {
                content += new String(chars, 0, count);
            }
        }

        log.info("result: {}", content);
    }

    @Test
    public void right2() throws IOException {
        log.info(
                "result: {}",
                Files.readAllLines(Paths.get("hello.txt"), Charset.forName("GBK")).stream()
                        .findFirst()
                        .orElse(""));
    }
}
