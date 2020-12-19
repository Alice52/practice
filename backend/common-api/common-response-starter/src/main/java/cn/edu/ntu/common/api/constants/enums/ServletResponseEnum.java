package cn.edu.ntu.common.api.constants.enums;

import javax.servlet.http.HttpServletResponse;

public enum ServletResponseEnum {
  MethodArgumentNotValidException(4400, "", HttpServletResponse.SC_BAD_REQUEST),
  MethodArgumentTypeMismatchException(4400, "", HttpServletResponse.SC_BAD_REQUEST),
  MissingServletRequestPartException(4400, "", HttpServletResponse.SC_BAD_REQUEST),
  MissingPathVariableException(4400, "", HttpServletResponse.SC_BAD_REQUEST),
  BindException(4400, "", HttpServletResponse.SC_BAD_REQUEST),
  MissingServletRequestParameterException(4400, "", HttpServletResponse.SC_BAD_REQUEST),
  TypeMismatchException(4400, "", HttpServletResponse.SC_BAD_REQUEST),
  ServletRequestBindingException(4400, "", HttpServletResponse.SC_BAD_REQUEST),
  HttpMessageNotReadableException(4400, "", HttpServletResponse.SC_BAD_REQUEST),
  NoHandlerFoundException(4404, "", HttpServletResponse.SC_NOT_FOUND),
  NoSuchRequestHandlingMethodException(4404, "", HttpServletResponse.SC_NOT_FOUND),
  HttpRequestMethodNotSupportedException(4405, "", HttpServletResponse.SC_METHOD_NOT_ALLOWED),
  HttpMediaTypeNotAcceptableException(4406, "", HttpServletResponse.SC_NOT_ACCEPTABLE),
  HttpMediaTypeNotSupportedException(4415, "", HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE),
  ConversionNotSupportedException(4500, "", HttpServletResponse.SC_INTERNAL_SERVER_ERROR),
  HttpMessageNotWritableException(4500, "", HttpServletResponse.SC_INTERNAL_SERVER_ERROR),
  AsyncRequestTimeoutException(4503, "", HttpServletResponse.SC_SERVICE_UNAVAILABLE);

  private Integer code;
  private String message;
  private Integer statusCode;

  ServletResponseEnum(Integer code, String message, Integer statusCode) {
    this.code = code;
    this.message = message;
    this.statusCode = statusCode;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public Integer getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(Integer statusCode) {
    this.statusCode = statusCode;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
