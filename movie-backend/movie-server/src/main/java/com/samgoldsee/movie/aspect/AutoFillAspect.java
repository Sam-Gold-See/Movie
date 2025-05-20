package com.samgoldsee.movie.aspect;

import com.samgoldsee.movie.annotation.AutoFill;
import com.samgoldsee.movie.constant.AutoFillConstant;
import com.samgoldsee.movie.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * 自定义切面，实现公共字段自动填充处理
 */
@Aspect
@Component
@Slf4j
public class AutoFillAspect {

    /**
     * 切入点
     */
    @Pointcut("execution(* com.samgoldsee.movie.mapper.*.*(..)) && @annotation(com.samgoldsee.movie.annotation.AutoFill)")
    public void autoFillPointCut() {
    }

    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint) {
        // 获取当前被拦截的方法上的数据库操作类型
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);
        if (autoFill == null)
            return;
        OperationType operationType = autoFill.operation();

        // 获取实体对象
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0 || args[0] == null)
            return;
        Object entity = args[0];

        // 准备赋值的数据
        LocalDateTime now = LocalDateTime.now();

        try {
            switch (operationType) {
                case INSERT:
                    setFieldValue(entity, AutoFillConstant.SET_CREATE_TIME, now);
                    setFieldValue(entity, AutoFillConstant.SET_UPDATE_TIME, now);
                    break;

                case UPDATE:
                    setFieldValue(entity, AutoFillConstant.SET_UPDATE_TIME, now);
                    break;

                default:
                    throw new IllegalArgumentException("Unsupported operation type: " + operationType);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while auto-filling entity fields", e);
        }
    }

    /**
     * 反射调用实体类的 set 方法，赋值对应字段
     */
    private void setFieldValue(Object entity, String methodName, Object value) throws Exception {
        Method method = entity.getClass().getDeclaredMethod(methodName, value.getClass());
        method.invoke(entity, value);
    }
}
