package com.biillrobot.study.spring.validte.handler;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler({ BindException.class })
  public ResultInfo<?> validationErrorHandler(BindException exception) {
    // 获取BindingResult对象
    BindingResult bindingResult = exception.getBindingResult();
    // 获取bindingResul中的所有错误
    List<ObjectError> allErrors = bindingResult.getAllErrors();
    //获取field和getMessage组合成map返回
    Map<String, String> collect = allErrors.stream().collect(Collectors.toMap(item -> ((FieldError) item).getField(),
      item -> item.getDefaultMessage(), (oldVal, currVal) -> oldVal));
    return new ResultInfo<>(-400, exception.getMessage(), collect);
  }
}