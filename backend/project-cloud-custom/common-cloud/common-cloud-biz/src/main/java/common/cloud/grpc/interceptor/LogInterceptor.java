package common.cloud.grpc.interceptor;

import io.grpc.*;
import lombok.extern.slf4j.Slf4j;

/**
 * @author asd <br>
 * @create 2021-06-28 3:31 PM <br>
 * @project common-core <br>
 */
@Slf4j
public class LogInterceptor implements ClientInterceptor, ServerInterceptor {

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(
            final MethodDescriptor<ReqT, RespT> method,
            final CallOptions callOptions,
            final Channel next) {

        log.info("grpc client log: {}", method.getFullMethodName());
        return next.newCall(method, callOptions);
    }

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
            ServerCall<ReqT, RespT> serverCall,
            Metadata metadata,
            ServerCallHandler<ReqT, RespT> serverCallHandler) {

        log.info("grpc server log: {}", serverCall.getMethodDescriptor().getFullMethodName());
        return serverCallHandler.startCall(serverCall, metadata);
    }
}
