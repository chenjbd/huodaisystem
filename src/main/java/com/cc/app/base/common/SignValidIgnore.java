package com.cc.app.base.common;

import java.lang.annotation.*;

/**
 * 忽略签名验证注解
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SignValidIgnore {
	boolean value() default  true;
}
