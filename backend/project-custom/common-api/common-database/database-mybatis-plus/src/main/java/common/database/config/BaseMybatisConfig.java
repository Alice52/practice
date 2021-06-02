package common.database.config;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import common.database.config.handler.MybatisMetaHandler;
import org.springframework.context.annotation.Bean;

/**
 * @author zack <br>
 * @create 2021-04-20 17:28 <br>
 * @project database-mybatis-plus <br>
 */
public abstract class BaseMybatisConfig {
    @Bean
    public GlobalConfig globalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setMetaObjectHandler(new MybatisMetaHandler());
        return globalConfig;
    }
}
