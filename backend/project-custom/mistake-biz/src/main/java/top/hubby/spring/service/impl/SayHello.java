package top.hubby.spring.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import top.hubby.spring.service.SayService;

/**
 * @author asd <br>
 * @create 2021-11-12 4:12 PM <br>
 * @project swagger-3 <br>
 */
@Service
@Slf4j
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SayHello extends SayService {

    @Override
    public void say() {
        super.say();
        log.info("hello");
    }
}
