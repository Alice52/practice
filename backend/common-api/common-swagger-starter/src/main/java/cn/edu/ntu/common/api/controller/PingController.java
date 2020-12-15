package cn.edu.ntu.common.api.controller;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author zack <br>
 * @create 2020/12/15 <br>
 * @project common-api <br>
 */
@ConditionalOnProperty(
    value = {"swagger3.ping"},
    havingValue = "true",
    matchIfMissing = true)
@RestController
public class PingController {

  @GetMapping("/ping")
  public HashMap ping() {
    return new HashMap(1) {
      {
        put("ping", "pong");
      }
    };
  }
}
