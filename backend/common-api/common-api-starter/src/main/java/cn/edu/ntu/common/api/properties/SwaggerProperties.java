package cn.edu.ntu.common.api.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zack <br>
 * @create 2020/12/14 <br>
 * @project common-api <br>
 */
@ConfigurationProperties(prefix = "swagger3")
public class SwaggerProperties {
  private Boolean enabled = true;
  private String author = "";
  private String email = "";
  private String groupName = "default";
  private String tryHost;
  private String applicationName = "";
  private String version = "V1.0.0";
  private String applicationDescription = "";
  private String tokenName = "BASE_TOKEN";
  private Boolean ping = true;

  public Boolean getPing() {
    return ping;
  }

  public void setPing(Boolean ping) {
    this.ping = ping;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

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

  public String getTokenName() {
    return tokenName;
  }

  public void setTokenName(String tokenName) {
    this.tokenName = tokenName;
  }
}
