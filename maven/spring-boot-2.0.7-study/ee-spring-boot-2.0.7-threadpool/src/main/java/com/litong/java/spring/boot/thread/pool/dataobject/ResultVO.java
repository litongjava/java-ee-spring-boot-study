package com.litong.java.spring.boot.thread.pool.dataobject;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResultVO<T> {

  // 0 成功 1失败 2用户没有登录
  private Integer code;

  private String msg;

  private T data;

  public ResultVO(Integer code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  /**
   * 请求成功  状态码 0
   *
   * @param msg 返回信息
   * @param <T> 类型
   * @return ResultVO
   */
  public static <T> ResultVO<Void> getSuccess(String msg) {
    return new ResultVO<Void>(0, msg);
  }

  /**
   * 请求成功  状态码 0
   *
   * @param msg  返回信息
   * @param data 返回对象
   * @param <T>  类型
   * @return ResultVO
   */
  public static <T> ResultVO<T> getSuccess(String msg, T data) {
    return new ResultVO<T>(1, msg, data);
  }

  /**
   * 请求失败   状态码 1
   *
   * @param msg 返回信息
   * @param <T> 类型
   * @return ResultVO
   */
  public static <T> ResultVO<Void> getFailed(String msg) {
    return new ResultVO<Void>(1, msg);
  }

  /**
   * 请求失败  状态 1
   *
   * @param msg  返回信息
   * @param data 返回数据
   * @param <T>  类型
   * @return ResultVO
   */
  public static <T> ResultVO<T> getFailed(String msg, T data) {
    return new ResultVO<T>(1, msg, data);
  }

  /**
   * 用户未登录
   *
   * @param <T> 类型
   * @return ResultVO
   */
  public static <T> ResultVO<Void> getNoLogin() {
    return new ResultVO<Void>(2, "用户未登录，请登录");
  }

  /**
   * 用户没有操作权限
   *
   * @param <T> 类型
   * @return ResultVO
   */
  public static <T> ResultVO<Void> getNoAuthorization() {
    return new ResultVO<Void>(3, "用户没有操作权限，请重新登录获取并且联系管理员添加权限");
  }
}