// package cn.edu.ntu.common.api.exception.handler;
//
// import cn.edu.ntu.common.api.exception.assertion.IBaseErrorResponse;
// import cn.edu.ntu.common.api.interceptor.RequestInterceptor;
// import cn.edu.ntu.common.api.utils.RequestUtil;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.slf4j.MDC;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
// import org.springframework.core.annotation.Order;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.RestControllerAdvice;
// import org.springframework.web.servlet.NoHandlerFoundException;
//
// import javax.servlet.http.HttpServletRequest;
//
/// **
// * This class is to handle {@link RequestInterceptor } cannot intercept 404.
// *
// * <p>If enable request-id feature, {@link NotFoundRequestHandler } will handle 404;<br>
// * And if disable exception handler, it will throw {@link NoHandlerFoundException } for external
// * exception handler to handle.
// *
// * @author zack <br>
// * @create 2020/12/19 <br>
// * @project common-api <br>
// */
// @RestControllerAdvice
// @Order(99)
// @ConditionalOnProperty(
//    prefix = "common.response.request-id",
//    value = {"enabled"},
//    havingValue = "true",
//    matchIfMissing = true)
// public class NotFoundRequestHandler {
//
//  private static final Logger LOGGER = LoggerFactory.getLogger(NotFoundRequestHandler.class);
//
//  @Value("${common.response.request-id.key:req-id}")
//  private String REQUEST_ID_KEY;
//
//  @Value("${common.response.handler.enabled:true}")
//  private boolean handlerEnabled;
//
//  /**
//   * This handler is for request-id, and if not enabled exception handler, so throw exception
//   * directly.
//   *
//   * @param e
//   * @return
//   * @throws NoHandlerFoundException
//   */
//  @ExceptionHandler(NoHandlerFoundException.class)
//  public IBaseErrorResponse handleException(HttpServletRequest request, NoHandlerFoundException e)
//      throws NoHandlerFoundException {
//
//    if (MDC.get(REQUEST_ID_KEY) == null) {
//      MDC.put(REQUEST_ID_KEY, RequestUtil.getRequestId(request, REQUEST_ID_KEY));
//    }
//
//    if (handlerEnabled) {
//      return RequestUtil.handleServletException(LOGGER, e);
//    }
//
//    throw e; // TODO: this will not handled by other exception handler.
//  }
// }
