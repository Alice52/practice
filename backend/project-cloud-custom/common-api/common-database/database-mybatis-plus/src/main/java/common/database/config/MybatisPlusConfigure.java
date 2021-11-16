package common.database.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import common.database.config.handler.DataScopeInterceptor;
import common.database.plugin.SensitivePlugin;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zack <br>
 * @create 2021-04-09 12:29 <br>
 * @project database-mybatis-plus <br>
 */
@Configuration
public class MybatisPlusConfigure extends BaseMybatisConfig {

    /**
     * 分页插件
     *
     * @return PaginationInterceptor
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 数据权限插件
     *
     * @return DataScopeInterceptor
     */
    @Bean
    public DataScopeInterceptor dataScopeInterceptor() {
        return new DataScopeInterceptor();
    }

    /**
     * 逻辑删除
     *
     * @return
     */
    /*
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }
    */

    /**
     * SQL执行效率插件 <br>
     * 且只有在 HikariDataSource 下才会生效参数<br>
     *
     * @return
     */
    /*
    @Bean
    @Profile({"dev", "cloud"})
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }
     */

    @Deprecated
    @ConditionalOnProperty(
            prefix = "common.global.database",
            value = {"de-sensitive"},
            havingValue = "true",
            matchIfMissing = false)
    public SensitivePlugin sensitivePlugin() {
        return new SensitivePlugin();
    }
}
