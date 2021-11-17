/**
 * https://www.renren.io
 *
 * <p>版权所有，侵权必究！
 */
package io.renren.config;

import io.renren.repository.*;
import io.renren.utils.RRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 数据库配置
 *
 * @author Mark sunlightcs@gmail.com
 */
@Configuration
public class DbConfig {
  @Value("${renren.database: mysql}")
  private String database;

  @Autowired private MySQLGeneratorRepository mySQLGeneratorRepository;
  @Autowired private OracleGeneratorRepository oracleGeneratorRepository;
  @Autowired private SQLServerGeneratorRepository sqlServerGeneratorRepository;
  @Autowired private PostgreSQLGeneratorRepository postgreSQLGeneratorRepository;

  private static boolean mongo = false;

  @Bean
  @Primary
  @Conditional(MongoNullCondition.class)
  public GeneratorRepository getGeneratorRepository() {
    if ("mysql".equalsIgnoreCase(database)) {
      return mySQLGeneratorRepository;
    } else if ("oracle".equalsIgnoreCase(database)) {
      return oracleGeneratorRepository;
    } else if ("sqlserver".equalsIgnoreCase(database)) {
      return sqlServerGeneratorRepository;
    } else if ("postgresql".equalsIgnoreCase(database)) {
      return postgreSQLGeneratorRepository;
    } else {
      throw new RRException("不支持当前数据库：" + database);
    }
  }

  @Bean
  @Primary
  @Conditional(MongoCondition.class)
  public GeneratorRepository getMongoDBRepository(
      MongoDBGeneratorRepository mongoDBGeneratorRepository) {
    mongo = true;
    return mongoDBGeneratorRepository;
  }

  public static boolean isMongo() {
    return mongo;
  }
}
