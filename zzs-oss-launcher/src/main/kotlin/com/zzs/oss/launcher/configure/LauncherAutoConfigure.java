package com.zzs.oss.launcher.configure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.ReactiveMongoTransactionManager;
import org.springframework.transaction.TransactionManager;

/**
 * @author 宋志宗 on 2022/5/9
 */
@Configuration
@EnableConfigurationProperties(LauncherProperties.class)
public class LauncherAutoConfigure {

  /** MongoDB事务管理器, 单机版MongoDB不支持此配置, 需要在配置文件中关闭此功能 */
  @Bean
  @ConditionalOnExpression("${launcher.enable-mongo-transaction-management:true}")
  public TransactionManager transactionManager(ReactiveMongoDatabaseFactory mongoDatabaseFactory) {
    return new ReactiveMongoTransactionManager(mongoDatabaseFactory);
  }
}
