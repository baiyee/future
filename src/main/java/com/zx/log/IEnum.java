package com.zx.log;

/**
 * 日志枚举转换
 *
 * @author : zhangxuan
 * @date : 2022/12/08 19:05
 */
public interface IEnum {

    /**
     * 将枚举值转化为字符串显示名
     * @return 显示名
     */
    Object getName();

    /**
     * 获取枚举值
     * @return 枚举值
     */
    Integer getCode();
}
