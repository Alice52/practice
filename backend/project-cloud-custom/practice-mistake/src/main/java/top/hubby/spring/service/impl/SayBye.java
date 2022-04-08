package top.hubby.spring.service.impl;

import lombok.extern.slf4j.Slf4j;
import top.hubby.spring.service.SayService;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * @author asd <br>
 * @create 2021-11-12 4:11 PM <br>
 * @project swagger-3 <br>
 */
@Service
@Slf4j
/** 这个是不对的 */
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SayBye extends SayService {

    @Override
    public void say() {
        super.say();
        log.info("bye");
    }
}
