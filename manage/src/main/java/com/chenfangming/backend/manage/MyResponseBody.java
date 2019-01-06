package com.chenfangming.backend.manage;

import java.util.List;
import lombok.Data;

/**
 * MyResponseBody.
 * @author 陈方明  cfmmail@sina.com
 * @since 2019-01-04 21:59
 */
@Data
public class MyResponseBody {
  private String Body1;
  private String Body2;
  private List<MyResponseBodyItem> myList;

}
