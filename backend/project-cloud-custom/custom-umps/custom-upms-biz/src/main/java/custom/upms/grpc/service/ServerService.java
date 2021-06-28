package custom.upms.grpc.service;


import custom.upms.api.grpc.HelloReply;
import custom.upms.api.grpc.HelloRequest;
import custom.upms.api.grpc.SimpleGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

/**
 * @author asd <br>
 * @create 2021-06-28 3:35 PM <br>
 * @project common-core <br>
 */
@GrpcService
public class ServerService extends SimpleGrpc.SimpleImplBase {
    @Override
    public void sayHello(
            final HelloRequest req, final StreamObserver<HelloReply> responseObserver) {
        final HelloReply reply =
                HelloReply.newBuilder().setMessage("Hello ==> " + req.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
