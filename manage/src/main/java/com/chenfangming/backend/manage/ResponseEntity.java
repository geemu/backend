package com.chenfangming.backend.manage;

import lombok.Data;

/**
 * ResponseEntity.
 * @author 陈方明  cfmmail@sina.com
 * @since 2019-01-04 21:59
 */
@Data
public class ResponseEntity<ResponseBody> {
  private ResponseHead Head;
  private ResponseBody Body;
}
