package custom.basic.grpc.service.client;

import custom.upms.api.grpc.HelloReply;
import custom.upms.api.grpc.HelloRequest;
import custom.upms.api.grpc.SimpleGrpc;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

/**
 * @author asd <br>
 * @create 2021-06-28 5:14 PM <br>
 * @project common-core <br>
 */
@Service
@Slf4j
public class UpmsClientService {
    @GrpcClient("CUSTOM-UPMS-SERVICE")
    private SimpleGrpc.SimpleBlockingStub simpleStub;

    public String sayHello(final String name) {
        try {
            final HelloReply response =
                    this.simpleStub.sayHello(HelloRequest.newBuilder().setName(name).build());
            return response.getMessage();
        } catch (final StatusRuntimeException e) {
            log.error("Request failed", e);
            return "FAILED with " + e.getStatus().getCode();
        }
    }
}
