package cn.edu.ntu.common.api.response.configure;

import cn.edu.ntu.common.api.utils.RequestUtil;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author zack <br>
 * @create 2020/12/19 <br>
 * @project common-api <br>
 */
@ConditionalOnProperty(
    prefix = "common.response.request-id",
    value = {"enabled"},
    havingValue = "true",
    matchIfMissing = true)
@Order(1)
@ControllerAdvice
public class ResponseBodyHeaderAdvice implements ResponseBodyAdvice {

  @Value("${common.response.request-id.key:req-id}")
  private String REQUEST_ID_KEY;

  @Override
  public boolean supports(MethodParameter methodParameter, Class aClass) {
    return true;
  }

  @Override
  public Object beforeBodyWrite(
      Object o,
      MethodParameter methodParameter,
      MediaType mediaType,
      Class aClass,
      ServerHttpRequest serverHttpRequest,
      ServerHttpResponse serverHttpResponse) {
    // Handle {@link RequestInterceptor } cannot intercept 404.
    if (MDC.get(REQUEST_ID_KEY) == null && serverHttpRequest instanceof ServletServerHttpRequest) {
      ServletServerHttpRequest request = (ServletServerHttpRequest) serverHttpRequest;
      MDC.put(
          REQUEST_ID_KEY, RequestUtil.getRequestId(request.getServletRequest(), REQUEST_ID_KEY));
    }

    serverHttpResponse.getHeaders().add(REQUEST_ID_KEY, MDC.get(REQUEST_ID_KEY));
    return o;
  }
}
