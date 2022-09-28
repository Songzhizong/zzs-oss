package com.zzs.oss.server.port.http

import com.zzs.oss.server.configure.OssServerProperties
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.http.codec.multipart.FilePart
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import java.io.SequenceInputStream

/**
 * @author 宋志宗 on 2022/9/28
 */
@RestController
@RequestMapping("/oss/file")
class FileController(
  private val properties: OssServerProperties
) {

  /**
   * 上传浏览器安全的文件
   *
   * @param bucket 文件桶
   * @param file   文件对象
   */
  @PostMapping("/upload/web_safe")
  suspend fun upload(bucket: String?, @RequestPart("file") file: FilePart) {
    val filename = file.filename()
    properties.riskyCheck(filename)
    val inputStream = file.content()
      .map { it.asInputStream(true) }
      .reduce { s1, s2 -> SequenceInputStream(s1, s2) }
      .awaitSingleOrNull()
  }
}
