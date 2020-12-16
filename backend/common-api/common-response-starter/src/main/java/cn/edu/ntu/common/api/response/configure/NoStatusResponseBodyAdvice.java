package cn.edu.ntu.common.api.response.configure;

import cn.edu.ntu.common.api.constants.enums.CommonResponseEnum;
import cn.edu.ntu.common.api.exception.assertion.IBaseErrorResponse;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;

/**
 * @author zack <br>
 * @create 2020/12/16 <br>
 * @project common-api <br>
 */
@ConditionalOnProperty(
    prefix = "common.response",
    value = {"noHttpStatus"},
    havingValue = "true",
    matchIfMissing = true)
@ControllerAdvice
public class NoStatusResponseBodyAdvice implements ResponseBodyAdvice {

  String[] ignores = new String[] {"/swagger-resources", "/v3/**", "/swagger-ui/**"};

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
      response.setStatusCode(HttpStatus.BAD_REQUEST);
      IBaseErrorResponse errorResponse = (IBaseErrorResponse) body;
      return new HashMap<String, Object>(3) {
        {
          put("code", errorResponse.getErrorCode());
          put("message", errorResponse.getErrorMsg());
          put("data", errorResponse.getParameters());
        }
      };
    }

    return new HashMap<String, Object>(3) {
      {
        put("code", CommonResponseEnum.SUCCESS.getErrorCode());
        put("message", CommonResponseEnum.SUCCESS.getErrorMsg());
        put("data", body);
      }
    };
  }
}
