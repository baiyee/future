package com.zx.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 操作字段，指定对象中需要比对的操作字段
 *
 * @author : zhangxuan
 * @date : 2022/12/08 19:05
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface OperationField {

    /**
     * @return 对象展示名
     */
    String displayName();

    /**
     * 枚举类型
     *
     * @return 枚举类型
     */
    Class<? extends IEnum> enumType() default DefaultIEnum.class;

    /**
     * @return 比较策略
     */
    CompareStrategyEnum compare() default CompareStrategyEnum.SINGLE_COMPARE;

}
