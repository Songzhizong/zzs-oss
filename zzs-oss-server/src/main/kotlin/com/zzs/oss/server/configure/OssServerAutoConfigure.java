package com.zzs.oss.server.configure;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author 宋志宗 on 2022/9/28
 */
@EnableScheduling
@ComponentScan("com.zzs.oss.server")
@EntityScan("com.zzs.oss.server.domain.model")
@EnableReactiveMongoRepositories("com.zzs.oss.server")
@EnableConfigurationProperties(OssServerProperties.class)
public class OssServerAutoConfigure {
}
