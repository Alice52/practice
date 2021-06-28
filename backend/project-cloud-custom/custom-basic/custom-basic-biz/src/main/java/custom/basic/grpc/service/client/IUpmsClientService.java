package custom.basic.grpc.service.client;

import io.grpc.StatusRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zack <br>
 * @create 2021-06-28<br>
 * @project project-cloud-custom <br>
 */
public interface IUpmsClientService {

    Logger log = LoggerFactory.getLogger(IUpmsClientService.class);

    /**
     * Grpc call say hello method for response.
     *
     * @param name
     * @return
     */
    String sayHello(final String name);

    /**
     * Upms say hello method fallback response.
     *
     * @param params
     * @param exception
     * @return
     */
    default String sayHello(String params, StatusRuntimeException exception) {
        log.info(
                "Grpc failed when call say hello method with params[{}], response[{}], and detail info:",
                params,
                exception.getStatus().getCode(),
                exception);

        return null;
    }
}
