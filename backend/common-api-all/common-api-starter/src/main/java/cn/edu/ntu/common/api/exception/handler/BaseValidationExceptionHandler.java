package cn.edu.ntu.common.api.exception.handler;

import cn.edu.ntu.common.api.constants.enums.CommonResponseEnum;
import cn.edu.ntu.common.api.exception.ListValidException;
import cn.edu.ntu.common.api.exception.assertion.IBaseErrorResponse;
import cn.edu.ntu.common.api.response.model.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.xml.bind.ValidationException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.util.StringUtils.isEmpty;

/**
 * This is bean validation exception handler.
 *
 * @author zack <br>
 * @create 2020/12/16 <br>
 * @project common-api <br>
 */
@ControllerAdvice
@Order(101)
@ConditionalOnProperty(
    prefix = "common.response.handler",
    value = {"enabled"},
    havingValue = "true",
    matchIfMissing = true)
public class BaseValidationExceptionHandler {
  private static final Logger LOGGER =
      LoggerFactory.getLogger(BaseValidationExceptionHandler.class);

  @ExceptionHandler(ValidationException.class)
  public IBaseErrorResponse handleValidationException(ValidationException ex) throws Exception {
    LOGGER.error(
        "validation bean error: type {}, params {}, message {}, detail {}",
        ex.getClass().getTypeName(),
        ex.getClass().getTypeParameters(),
        ex.getMessage(),
        ex);

    Throwable cause = ex.getCause();
    Map<String, Object> collect = new HashMap<>(16);

    if (cause instanceof ListValidException) {
      Map<Integer, Set<ConstraintViolation<Object>>> errors =
          ((ListValidException) cause).getErrors();

      errors.forEach(
          (i, error) -> {
            error.stream()
                .parallel()
                .forEach(
                    x ->
                        collect.put(
                            "[" + i + "]." + x.getPropertyPath().toString(),
                            new HashMap<String, Object>(2) {
                              {
                                put("rejectValue", x.getInvalidValue());
                                put("message", x.getMessage());
                              }
                            }));
          });
    } else {
      collect.put("message", ex.getMessage());
      collect.put("exception type", ex.getClass().getTypeName());
    }

    return new ErrorResponse(CommonResponseEnum.BEAN_VALIDATION, collect);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public IBaseErrorResponse handleConstraintViolationException(ConstraintViolationException ex) {

    Map<String, Object> collect =
        ex.getConstraintViolations().stream()
            .parallel()
            .collect(
                Collectors.toMap(
                    x -> subAfter(x.getPropertyPath().toString(), ".", false),
                    x ->
                        new HashMap<String, Object>(2) {
                          {
                            put("rejectValue", x.getInvalidValue());
                            put("message", x.getMessage());
                          }
                        },
                    (s, a) -> Arrays.asList(s, a)));

    return new ErrorResponse(CommonResponseEnum.BEAN_VALIDATION, collect);
  }

  @ExceptionHandler(BindException.class)
  public IBaseErrorResponse handleBindException(BindException ex) {

    return getErrorResults(ex.getBindingResult(), ex);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public IBaseErrorResponse handleMethodArgumentNotValidException(
      MethodArgumentNotValidException ex) {

    return getErrorResults(ex.getBindingResult(), ex);
  }

  private IBaseErrorResponse getErrorResults(BindingResult bindingResult, Exception ex) {

    LOGGER.error(
        "validation bean error: type {}, params {}, message {}, detail {}",
        ex.getClass().getTypeName(),
        ex.getClass().getTypeParameters(),
        ex.getMessage(),
        ex);

    Map<String, Object> collect =
        bindingResult.getFieldErrors().stream()
            .parallel()
            .collect(
                Collectors.toMap(
                    FieldError::getField,
                    x ->
                        new HashMap<String, Object>(2) {
                          {
                            put("rejectValue", x.getRejectedValue());
                            put("message", x.getDefaultMessage());
                          }
                        },
                    (s, a) -> Arrays.asList(s, a)));

    return new ErrorResponse(CommonResponseEnum.BEAN_VALIDATION, collect);
  }

  private static String subAfter(
      CharSequence string, CharSequence separator, boolean isLastSeparator) {
    if (isEmpty(string)) {
      return null == string ? null : string.toString();
    }
    if (separator == null) {
      return "";
    }
    final String str = string.toString();
    final String sep = separator.toString();
    final int pos = isLastSeparator ? str.lastIndexOf(sep) : str.indexOf(sep);
    if (-1 == pos || (string.length() - 1) == pos) {
      return "";
    }
    return str.substring(pos + separator.length());
  }
}
