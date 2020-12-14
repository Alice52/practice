package cn.edu.ntu.common.api.test.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zack <br>
 * @create 2020/12/15 <br>
 * @project common-api <br>
 */
@Api
@RestController
public class SwaggerController {

  @GetMapping("/info")
  public String swagger() {
    return "swagger 3.0";
  }
}
