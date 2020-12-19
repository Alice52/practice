package cn.edu.ntu.common.api.interceptor;

import cn.edu.ntu.common.api.utils.RequestUtil;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class cannot interceptor 404.
 *
 * <p>It can't really send every single incoming request to the interceptors before determining if
 * there's a mapping.<br>
 * Notice that one of the arguments to the interceptor is the Controller itself! <br>
 *
 * <p>If the URL that's coming in is one that's not mapped anywhere, how is it supposed to know what
 * handler that would be to pass in as a method argument?
 *
 * <p>URL rewriting needs to be done further up the stack, before the request hits the dispatcher
 * servlet.
 *
 * @author zack <br>
 * @create 2020/12/17 <br>
 * @project common-api <br>
 */
@ConditionalOnProperty(
    prefix = "common.response.request-id",
    value = {"enabled"},
    havingValue = "true",
    matchIfMissing = true)
@Component
public class RequestInterceptor extends HandlerInterceptorAdapter implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(this).order(1);
  }

  /** if use responseProperties, will throw exception due to responseProperties is null now. */
  @Value("${common.response.request-id.key:req-id}")
  private String REQUEST_ID_KEY;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    MDC.put(REQUEST_ID_KEY, RequestUtil.getRequestId(request, REQUEST_ID_KEY));
    return super.preHandle(request, response, handler);
  }

  /**
   * link:
   * https://stackoverflow.com/questions/48823794/spring-interceptor-doesnt-add-header-to-restcontroller-services
   *
   * <p>This addHeader will not work due to annotation of @RestController. <br>
   * But it can work by using @Controller<br>
   *
   * <p>HandlerInterceptorAdapters can not working with @ResponseBody and ResponseEntity methods,
   * <br>
   * because those are handled by HttpMessageConverter which writes to response <br>
   * before postHandle is called which makes it difficult to change the response.<br>
   *
   * <p>@RestController can use ResponseBodyAdvice to make addHeader worked.
   *
   * @param request
   * @param response
   * @param handler
   * @param ex
   */
  @Override
  public void afterCompletion(
      HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    response.addHeader(REQUEST_ID_KEY, MDC.get(REQUEST_ID_KEY));
    MDC.remove(REQUEST_ID_KEY);
  }
}
