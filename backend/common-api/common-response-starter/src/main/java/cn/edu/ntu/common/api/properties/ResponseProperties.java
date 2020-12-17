package cn.edu.ntu.common.api.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * This class is no used.
 *
 * @author zack <br>
 * @create 2020/12/17 <br>
 * @project common-api <br>
 */
@ConfigurationProperties(prefix = "common.response")
public class ResponseProperties {

  private Boolean enabled = true;
  private RequestProperties requestId = new RequestProperties();
  private ResponseAdvice advice = new ResponseAdvice();
  private HandlerEnabled handler = new HandlerEnabled();

  public class HandlerEnabled {
    private Boolean enabled = true;

    public Boolean getEnabled() {
      return enabled;
    }

    public void setEnabled(Boolean enabled) {
      this.enabled = enabled;
    }
  }

  public class ResponseAdvice {
    private String codeName = "code";
    private String messageName = "message";
    private String dataName = "data";
    private Integer failedApiStatus = 400;

    public String getCodeName() {
      return codeName;
    }

    public void setCodeName(String codeName) {
      this.codeName = codeName;
    }

    public String getMessageName() {
      return messageName;
    }

    public void setMessageName(String messageName) {
      this.messageName = messageName;
    }

    public String getDataName() {
      return dataName;
    }

    public void setDataName(String dataName) {
      this.dataName = dataName;
    }

    public Integer getFailedApiStatus() {
      return failedApiStatus;
    }

    public void setFailedApiStatus(Integer failedApiStatus) {
      this.failedApiStatus = failedApiStatus;
    }
  }

  public class RequestProperties {
    private Boolean enabled = true;
    private String requestId = "req-id";

    public Boolean getEnabled() {
      return enabled;
    }

    public void setEnabled(Boolean enabled) {
      this.enabled = enabled;
    }

    public String getRequestId() {
      return requestId;
    }

    public void setRequestId(String requestId) {
      this.requestId = requestId;
    }
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  public RequestProperties getRequestId() {
    return requestId;
  }

  public void setRequestId(RequestProperties requestId) {
    this.requestId = requestId;
  }

  public ResponseAdvice getAdvice() {
    return advice;
  }

  public void setAdvice(ResponseAdvice advice) {
    this.advice = advice;
  }

  public HandlerEnabled getHandler() {
    return handler;
  }

  public void setHandler(HandlerEnabled handler) {
    this.handler = handler;
  }
}
