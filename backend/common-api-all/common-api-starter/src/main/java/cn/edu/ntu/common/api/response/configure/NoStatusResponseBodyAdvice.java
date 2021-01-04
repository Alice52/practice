package cn.edu.ntu.common.api.response.configure;

import cn.edu.ntu.common.api.constants.enums.CommonResponseEnum;
import cn.edu.ntu.common.api.exception.assertion.IBaseErrorResponse;
import cn.edu.ntu.common.api.properties.ResponseProperties;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zack <br>
 * @create 2020/12/16 <br>
 * @project common-api <br>
 */
@ConditionalOnProperty(
    prefix = "common.response.advice",
    value = {"enabled"},
    havingValue = "true",
    matchIfMissing = true)
@ControllerAdvice
public class NoStatusResponseBodyAdvice implements ResponseBodyAdvice {

  private final List<Integer> statuses;

  public NoStatusResponseBodyAdvice() {
    statuses =
        Arrays.asList(HttpStatus.values()).stream()
            .map(x -> x.value())
            .collect(Collectors.toList());
  }

  @Resource ResponseProperties responseProperties;

  /** if use responseProperties, will throw exception due to responseProperties is null now. */
  @Value("${common.response.request-id.key:req-id}")
  private String requestIdKey;

  @Value("${common.response.advice.code-name:code}")
  private String codeName;

  @Value("${common.response.advice.message-name:message}")
  private String messageName;

  @Value("${common.response.advice.data-name:data}")
  private String dataName;

  @Value("${common.response.advice.failed-api-status:400}")
  private int failedHttpCode;

  String[] ignores = new String[] {"/error", "/swagger-resources", "/v3/api-docs", "/swagger-ui"};

  boolean ignoring(String uri) {
    for (String string : ignores) {
      if (uri.contains(string)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean supports(MethodParameter methodParameter, Class aClass) {
    return true;
  }

  /**
   * If api is failed, then set http status to 400. <br>
   * Else set http status to 200. <br>
   *
   * @param body
   * @param methodParameter
   * @param mediaType
   * @param aClass
   * @param request
   * @param response
   * @return
   */
  @Override
  public Object beforeBodyWrite(
      Object body,
      MethodParameter methodParameter,
      MediaType mediaType,
      Class aClass,
      ServerHttpRequest request,
      ServerHttpResponse response) {

    if (this.ignoring(request.getURI().toString())) {
      return body;
    }

    if (body != null && body instanceof IBaseErrorResponse) {
      if (!statuses.contains(failedHttpCode)) {
        failedHttpCode = 400;
      }

      response.setStatusCode(HttpStatus.valueOf(failedHttpCode));
      IBaseErrorResponse errorResponse = (IBaseErrorResponse) body;
      return new HashMap<String, Object>(4) {
        {
          if (responseProperties.getRequestId().getEnabled()) {
            put(requestIdKey, MDC.get(requestIdKey));
          }

          put(codeName, errorResponse.getErrorCode());
          put(messageName, errorResponse.getErrorMsg());
          put(dataName, errorResponse.getParameters());
        }
      };
    }

    return new HashMap<String, Object>(4) {
      {
        if (responseProperties.getRequestId().getEnabled()) {
          put(requestIdKey, MDC.get(requestIdKey));
        }

        put(codeName, CommonResponseEnum.SUCCESS.getErrorCode());
        put(messageName, CommonResponseEnum.SUCCESS.getErrorMsg());
        put(dataName, body);
      }
    };
  }
}
