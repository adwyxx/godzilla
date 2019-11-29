package com.adwyxx.godzilla.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义标识注解，忽略Authorize验证
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019/11/28 17:47
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreAuthorization {
}
