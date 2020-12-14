package cn.edu.ntu.common.api.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zack <br>
 * @create 2020/12/14 <br>
 * @project common-api <br>
 */
@ConfigurationProperties(prefix = "swagger3")
public class SwaggerProperties {
  private Boolean enable;
  private String author;
  private String email;
  private String groupName;
  private String tryHost;
  private String applicationName;
  private String version;
  private String applicationDescription;

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public Boolean getEnable() {
    return enable;
  }

  public void setEnable(Boolean enable) {
    this.enable = enable;
  }

  public String getTryHost() {
    return tryHost;
  }

  public void setTryHost(String tryHost) {
    this.tryHost = tryHost;
  }

  public String getApplicationName() {
    return applicationName;
  }

  public void setApplicationName(String applicationName) {
    this.applicationName = applicationName;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getApplicationDescription() {
    return applicationDescription;
  }

  public void setApplicationDescription(String applicationDescription) {
    this.applicationDescription = applicationDescription;
  }
}
