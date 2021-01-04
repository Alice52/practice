package cn.edu.ntu.common.api.controller;

import cn.edu.ntu.common.api.response.ErrorResponse;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ConcurrentHashMap;

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
@ApiResponses({
  @ApiResponse(
      code = 400,
      message = "Bad Request",
      response = ErrorResponse.class,
      examples = @Example({@ExampleProperty(value = "code", mediaType = "")})),
  @ApiResponse(code = 500, message = "Internal Error", response = ErrorResponse.class)
})
public class PingController {

  @GetMapping("/ping")
  public ConcurrentHashMap ping() {
    return new ConcurrentHashMap<String, String>(1) {
      {
        put("ping", "pong");
      }
    };
  }

  @GetMapping(value = "/string")
  public String string() {
    return "string";
  }
}
