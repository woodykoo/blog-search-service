package com.woody.domain.aop.annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * Created by woody 2023.03.19
 * Redisson 이용한 분산락을 설정하기 윈한 어노테이션
 * */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedissonLock {

    TimeUnit timeUnit() default  TimeUnit.MILLISECONDS;

    long waitTime() default 1000;

    long leaseTime() default 5000;

    String key();

    String prefix() default "lock";
}
