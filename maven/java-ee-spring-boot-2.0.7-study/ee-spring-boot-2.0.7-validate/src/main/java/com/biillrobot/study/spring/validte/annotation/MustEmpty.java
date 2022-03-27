package com.biillrobot.study.spring.validte.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy=MustEmptyValidator.class)
@Documented
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MustEmpty {
  String message() default "属性必须为空";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
