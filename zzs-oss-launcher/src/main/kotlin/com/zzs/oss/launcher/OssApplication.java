package com.zzs.oss.launcher;

import com.zzs.oss.launcher.configure.WebMessageConverterAutoConfigure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.nativex.hint.*;

/**
 * @author 宋志宗 on 2022/9/5
 */
@NativeHint(
  jdkProxies = {
    @JdkProxyHint(
      types = {
        org.springframework.data.mongodb.core.mapping.Document.class,
        org.springframework.core.annotation.SynthesizedAnnotation.class
      }
    ),
    @JdkProxyHint(
      types = {
        com.mongodb.reactivestreams.client.MongoDatabase.class,
        org.springframework.aop.SpringProxy.class,
        org.springframework.core.DecoratingProxy.class
      }
    ),
    @JdkProxyHint(
      types = {
        com.mongodb.reactivestreams.client.MongoCollection.class,
        org.springframework.aop.SpringProxy.class,
        org.springframework.core.DecoratingProxy.class
      }
    ),
  },
  types = {
    @TypeHint(types = WebMessageConverterAutoConfigure.class, access = {TypeAccess.PUBLIC_FIELDS, TypeAccess.DECLARED_FIELDS, TypeAccess.PUBLIC_CONSTRUCTORS, TypeAccess.DECLARED_CONSTRUCTORS, TypeAccess.PUBLIC_METHODS, TypeAccess.DECLARED_METHODS})
  }
)
@SpringBootApplication(proxyBeanMethods = false)
public class OssApplication {
  private static final Logger log = LoggerFactory.getLogger(OssApplication.class);

  public static void main(String[] args) {
    int ioWorkerCount = Runtime.getRuntime().availableProcessors();
    System.setProperty("reactor.netty.ioWorkerCount", String.valueOf(ioWorkerCount));
    System.setProperty("reactor.netty.pool.leasingStrategy", "lifo");
    SpringApplication.run(OssApplication.class, args);
    log.info("io worker threads: {}", ioWorkerCount);
  }
}
