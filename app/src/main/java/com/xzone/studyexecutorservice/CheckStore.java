package com.xzone.studyexecutorservice;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by xl on 2018/8/3.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckStore {
    int high() default 100;
    int low() default 0;
    String warn() default "错了啊";
    String value() default "";
}
