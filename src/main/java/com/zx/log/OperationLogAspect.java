package com.zx.log;

import com.zx.config.SpringBeanUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 操作日志aop
 *
 * @author : zhangxuan
 * @date : 2022/12/08 19:05
 */
@Aspect
@Component
public class OperationLogAspect {

    @Pointcut("@annotation(com.zx.log.OperationLog)")
    public void pointcut() {

    }

    @Around(value = "pointcut() && @annotation(log)")
    public Object around(ProceedingJoinPoint point, OperationLog log) throws Throwable {
        Object result;

        try {
            result = point.proceed();

            OperationLogCallback callback = SpringBeanUtils.getBean(log.callback());

            DataComparator comparator = SpringBeanUtils.getBean(log.comparator());

            List<OperationResult> ops = comparator.comparator();

            for (OperationResult op : ops) {
                op.addOperation(log);
                op.addUser();
            }
            callback.callback(ops);

        } finally {

            OperationContext.clean();
        }
        return result;
    }

}
