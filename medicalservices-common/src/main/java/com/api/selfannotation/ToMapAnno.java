package com.api.selfannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解javabean属性(带注解的属性直接转成map)
 * Created by cjh on 2018/6/1.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ToMapAnno {
    String name() default "";
}
