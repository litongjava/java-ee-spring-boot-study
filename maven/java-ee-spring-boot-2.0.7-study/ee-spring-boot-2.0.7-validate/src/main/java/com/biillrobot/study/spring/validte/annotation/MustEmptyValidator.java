package com.biillrobot.study.spring.validte.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MustEmptyValidator implements ConstraintValidator<MustEmpty, String> {

  @Override
  public boolean isValid(String input, ConstraintValidatorContext context) {
    log.info("input={}", input);
    // 验证通过返回true
    if (StringUtils.isEmpty(input)) {
      log.info("验证通过");
      return true;
    }
    log.info("验证失败");
    // 验证失败返回false
    return false;
  }
}
