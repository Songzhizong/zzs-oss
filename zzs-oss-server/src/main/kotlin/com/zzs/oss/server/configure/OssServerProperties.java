package com.zzs.oss.server.configure;

import com.zzs.framework.core.exception.BadRequestException;
import com.zzs.framework.core.lang.Sets;
import com.zzs.framework.core.lang.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.Nullable;
import java.util.Set;

/**
 * @author 宋志宗 on 2022/9/28
 */
@ConfigurationProperties("zzs-oss.server")
public class OssServerProperties {
  private Set<String> riskySuffix = Sets.of(
    ".html", ".htm", ".xhtml", ".shtml", ".js", ".jsp", ".jspa", ".jspx", ".jspf",
    ".jsw", ".jsv", ".jhtml", ".jtml", ".php", ".psp", ".pht", ".phtml", ".php1", ".php2",
    ".php3", ".php4", ".php5", ".php7", ".asp", ".aspx", ".asa", ".asax", ".ascx", ".ashx",
    ".asmx", ".cer", ".der", ".crt", ".sh", ".exe", ".swf", ".htaccess"
  );

  /** 文件类型风险性校验 */
  public void riskyCheck(@Nullable String filename) {
    if (StringUtils.isBlank(filename)) {
      return;
    }
    int lastIndex = filename.lastIndexOf(".");
    if (lastIndex < 0) {
      return;
    }
    String suffix = filename.substring(lastIndex).toLowerCase();
    if (riskySuffix.contains(suffix)) {
      throw new BadRequestException("非法的文件类型");
    }
  }

  public Set<String> getRiskySuffix() {
    return riskySuffix;
  }

  public OssServerProperties setRiskySuffix(Set<String> riskySuffix) {
    this.riskySuffix = riskySuffix;
    return this;
  }
}
