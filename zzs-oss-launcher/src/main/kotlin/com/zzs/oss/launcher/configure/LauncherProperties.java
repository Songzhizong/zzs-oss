package com.zzs.oss.launcher.configure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 宋志宗 on 2022/5/9
 */
@ConfigurationProperties("launcher")
public class LauncherProperties {

  /** 是否启用事务管理器 */
  private boolean enableMongoTransactionManagement = true;

  public boolean isEnableMongoTransactionManagement() {
    return enableMongoTransactionManagement;
  }

  public void setEnableMongoTransactionManagement(boolean enableMongoTransactionManagement) {
    this.enableMongoTransactionManagement = enableMongoTransactionManagement;
  }
}
