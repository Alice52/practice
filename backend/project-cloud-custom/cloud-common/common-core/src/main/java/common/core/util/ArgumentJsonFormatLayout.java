package common.core.util;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import cn.hutool.json.JSONUtil;
import org.slf4j.helpers.MessageFormatter;

import java.util.stream.Stream;

/**
 * This class is used in logback-spring.xml to log Object property.<br>
 *
 * @author zack <br>
 * @create 2021-06-02 14:44 <br>
 * @project custom-test <br>
 */
@Deprecated
public class ArgumentJsonFormatLayout extends MessageConverter {
    @Override
    public String convert(ILoggingEvent event) {
        try {
            return MessageFormatter.arrayFormat(
                            event.getMessage(),
                            Stream.of(event.getArgumentArray()).map(JSONUtil::toJsonStr).toArray())
                    .getMessage();
        } catch (Exception e) {
            return event.getMessage();
        }
    }
}
