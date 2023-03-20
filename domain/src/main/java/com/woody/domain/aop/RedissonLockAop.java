package com.woody.domain.aop;

import com.woody.domain.aop.annotaion.RedissonLock;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by woody 2023.03.19
 * Redisson 이용한 분산락 AOP
 * */
@Aspect
@Component
@RequiredArgsConstructor
public class RedissonLockAop {

    private final RedissonClient redissonClient;

    private final RedissonCallTransaction redissonCallTransaction;

    @Pointcut("@annotation(com.woody.domain.aop.annotaion.RedissonLock)")
    public void pointcut() {}

    @Around("pointcut()")
    public Object lock(final ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        RedissonLock redissonLock = method.getAnnotation(RedissonLock.class);

        String key = createKey(redissonLock.prefix(), redissonLock.key(), signature.getParameterNames(), joinPoint.getArgs());
        RLock rock = redissonClient.getLock(key);

        try {
            if (rock.tryLock(redissonLock.waitTime(), redissonLock.leaseTime(), redissonLock.timeUnit()))  {
                return redissonCallTransaction.proceed(joinPoint);
            }
            return false;
        } finally {
            if (rock.isLocked()) {
                rock.unlock();
            }
        }
    }

    private String createKey(String prefix, String key, String[] parameterNames, Object[] args) {
        StringBuilder stringBuilder = new StringBuilder(prefix).append(":").append(key).append(":");

        for (int i = 0; i < parameterNames.length; i++) {
            if (parameterNames[i].equals(key)) {
                stringBuilder.append(args[i]);
                break;
            }
        }
        return stringBuilder.toString();
    }
}
