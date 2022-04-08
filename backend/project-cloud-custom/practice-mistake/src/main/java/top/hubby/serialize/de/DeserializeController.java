package top.hubby.serialize.de;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.swagger.annotations.Api;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author asd <br>
 * @create 2021-10-29 1:34 PM <br>
 * @project swagger-3 <br>
 */
@Api(tags = "Serialize")
@Slf4j
@RestController
@RequestMapping("/serialize/des")
public class DeserializeController {
    @Autowired ObjectMapper objectMapper;

    @GetMapping("/wrong")
    public void wrong() throws IOException {
        log.info("result :{}", objectMapper.readValue("{\"code\":1234}", APIResultWrong.class));
        log.info("result :{}", objectMapper.readValue("{\"code\":2000}", APIResultWrong.class));
    }

    @GetMapping("/right")
    public void right() throws IOException {
        log.info("result :{}", objectMapper.readValue("{\"code\":1234}", APIResultRight.class));
        log.info("result :{}", objectMapper.readValue("{\"code\":2000}", APIResultRight.class));
    }

    @SneakyThrows
    @GetMapping("/test")
    public void test() {

        String asString = objectMapper.writeValueAsString(Color.BLUE);
        log.info("ser color:{}", asString);
        log.info("des color:{}", objectMapper.readValue(asString, Color.class));

        objectMapper.enable(SerializationFeature.WRITE_ENUMS_USING_INDEX);
        asString = objectMapper.writeValueAsString(Color.BLUE);
        log.info("color:{}", asString);
        log.info("des color:{}", objectMapper.readValue(asString, Color.class));
    }

    enum Color {
        RED,
        BLUE
    }
}
