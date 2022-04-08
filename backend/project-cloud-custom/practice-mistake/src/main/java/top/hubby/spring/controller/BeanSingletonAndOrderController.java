package top.hubby.spring.controller;

import java.util.List;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import top.hubby.spring.service.SayService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author asd <br>
 * @create 2021-11-12 4:10 PM <br>
 * @project swagger-3 <br>
 */
@Slf4j
@Api(tags = "Spring")
@RestController
@RequestMapping("/spring/scope")
public class BeanSingletonAndOrderController {

    @Autowired List<SayService> sayServiceList;
    @Autowired private ApplicationContext applicationContext;

    @GetMapping("test")
    public void test() {
        log.info("====================");
        sayServiceList.forEach(SayService::say);
    }

    @GetMapping("test2")
    public void test2() {
        log.info("====================");
        applicationContext.getBeansOfType(SayService.class).values().forEach(SayService::say);
    }
}
