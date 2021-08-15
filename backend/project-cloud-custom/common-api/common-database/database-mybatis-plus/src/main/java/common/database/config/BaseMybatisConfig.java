package common.database.config;

import cn.hutool.core.date.DatePattern;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import common.core.jackson.JavaTimeModule;
import common.database.config.handler.MybatisMetaHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author zack <br>
 * @create 2021-04-20 17:28 <br>
 * @project database-mybatis-plus <br>
 */
public abstract class BaseMybatisConfig {

    @Bean
    public MybatisSqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
        MybatisSqlSessionFactoryBean mybatisPlus = new MybatisSqlSessionFactoryBean();
        // 加载数据源
        mybatisPlus.setDataSource(dataSource);
        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        // 配置填充器
        globalConfig.setMetaObjectHandler(new MybatisMetaHandler());
        mybatisPlus.setGlobalConfig(globalConfig);

        return mybatisPlus;
    }

    /**
     * This will help to java8 time standard.
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(Jackson2ObjectMapperBuilderCustomizer.class)
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        return builder -> {
            builder.locale(Locale.CHINA);
            builder.timeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
            builder.simpleDateFormat(DatePattern.NORM_DATETIME_PATTERN);
            builder.modules(new JavaTimeModule());
        };
    }
}
