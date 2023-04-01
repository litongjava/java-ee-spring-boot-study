package top.ppnt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResultModel<T> {
  private int code;
  private String msg;
  private T data;
}