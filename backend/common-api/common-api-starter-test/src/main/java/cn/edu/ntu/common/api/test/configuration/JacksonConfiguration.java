package cn.edu.ntu.common.api.test.configuration;

import cn.edu.ntu.common.api.test.utils.TimeUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonTokenId;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author zack <br>
 * @create 2020/12/16 <br>
 * @project common-api <br>
 */
@Slf4j
@Configuration
public class JacksonConfiguration {
  @Bean
  public ObjectMapper getObjectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    JavaTimeModule javaTimeModule = new JavaTimeModule();
    javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
    javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer());
    javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer());
    javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
    javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer());
    javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer());
    objectMapper.registerModule(javaTimeModule);
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return objectMapper;
  }

  public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

    @Override
    public void serialize(
        LocalDateTime value, JsonGenerator jsonGenerator, SerializerProvider provider)
        throws IOException {
      if (value == null) {
        jsonGenerator.writeNull();
      } else {
        jsonGenerator.writeNumber(TimeUtil.toMilli(value));
      }
    }
  }

  public class LocalDateSerializer extends JsonSerializer<LocalDate> {

    @Override
    public void serialize(LocalDate value, JsonGenerator jsonGenerator, SerializerProvider provider)
        throws IOException {
      if (value == null) {
        jsonGenerator.writeNull();
      } else {
        jsonGenerator.writeNumber(TimeUtil.toMilli(value));
      }
    }
  }

  public class LocalTimeSerializer extends JsonSerializer<LocalTime> {

    @Override
    public void serialize(LocalTime value, JsonGenerator jsonGenerator, SerializerProvider provider)
        throws IOException {
      if (value == null) {
        jsonGenerator.writeNull();
      } else {
        jsonGenerator.writeNumber(TimeUtil.toMilli(value));
      }
    }
  }

  public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(
        JsonParser parser, DeserializationContext deserializationContext)
        throws IOException, JsonProcessingException {
      if (parser.hasTokenId(JsonTokenId.ID_NUMBER_INT)) {
        String string = parser.getText().trim();
        if (string.length() == 0) {
          return null;
        }

        long timestamp = Long.parseLong(string);
        return TimeUtil.toLocalDateTime(timestamp);
      }
      return null;
    }
  }

  public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {
    @Override
    public LocalDate deserialize(JsonParser parser, DeserializationContext deserializationContext)
        throws IOException, JsonProcessingException {
      if (parser.hasTokenId(JsonTokenId.ID_NUMBER_INT)) {
        String string = parser.getText().trim();
        if (string.length() == 0) {
          return null;
        }

        long timestamp = Long.parseLong(string);
        return TimeUtil.toLocalDate(timestamp);
      }
      return null;
    }
  }

  public class LocalTimeDeserializer extends JsonDeserializer<LocalTime> {
    @Override
    public LocalTime deserialize(JsonParser parser, DeserializationContext deserializationContext)
        throws IOException, JsonProcessingException {
      if (parser.hasTokenId(JsonTokenId.ID_NUMBER_INT)) {
        String string = parser.getText().trim();
        if (string.length() == 0) {
          return null;
        }

        long timestamp = Long.parseLong(string);
        return TimeUtil.toLocalTime(timestamp);
      }
      return null;
    }
  }
}
