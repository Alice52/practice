package custom.upms.grpc.service.server;

import custom.upms.api.grpc.HelloRequest;
import custom.upms.api.grpc.HelloResponse;
import custom.upms.api.grpc.SimpleGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

/**
 * Grpc exception will not handle bu default exception handler.
 *
 * @author asd <br>
 * @create 2021-06-28 3:35 PM <br>
 * @project common-core <br>
 */
@GrpcService
public class UpmsServerService extends SimpleGrpc.SimpleImplBase {

    @Override
    public void sayHello(
            final HelloRequest req, final StreamObserver<HelloResponse> responseObserver) {
        final HelloResponse reply =
                HelloResponse.newBuilder().setMessage("Hello ==> " + req.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
