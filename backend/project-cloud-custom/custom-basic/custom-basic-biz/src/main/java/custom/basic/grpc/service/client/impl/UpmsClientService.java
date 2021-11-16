package custom.basic.grpc.service.client.impl;

import custom.basic.grpc.annotation.FallbackAnno;
import custom.basic.grpc.service.client.IUpmsClientService;
import custom.upms.api.grpc.HelloRequest;
import custom.upms.api.grpc.HelloResponse;
import custom.upms.api.grpc.SimpleGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

/**
 * @author asd <br>
 * @create 2021-06-28 5:14 PM <br>
 * @project common-core <br>
 */
@Service
public class UpmsClientService implements IUpmsClientService {
    @GrpcClient("upms-service")
    private SimpleGrpc.SimpleBlockingStub simpleStub;

    @FallbackAnno
    @Override
    public String sayHello(final String name) {
        final HelloResponse response =
                simpleStub.sayHello(HelloRequest.newBuilder().setName(name).build());
        return response.getMessage();
    }
}
