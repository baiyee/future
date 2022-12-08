package com.zx.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 操作日志
 *
 * @author : zhangxuan
 * @date : 2022/12/08 19:05
 */
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationLog {

    /**
     * 操作类型的名称，展示用户操作的描述，例如编辑，审核等
     *
     * @return 操作类型的名称
     */
    String typeName();

    /**
     * 服务名，查询服务的标识
     * @return 服务名
     */
    String serviceName();

    /**
     * 操作日志处理回调，可实现自定义日志处理和存储。需注意实现的类必须由spring ioc管理
     *
     * @return 必须实现OperationLogCallback接口。
     */
    Class<? extends OperationLogCallback> callback() default CompareOperationLogCallback.class;

    /**
     * 数据比较器，默认是单个对象比较器
     *
     * @return 数据比较器
     */
    Class<? extends DataComparator> comparator() default SingleObjectDataComparator.class;
}
