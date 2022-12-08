package com.zx.log;

/**
 * 比较策略，决定日志记录的方式，是比较修改前后数据还是存储list，还是存储修改前后的图片
 *
 * @author : zhangxuan
 * @date : 2022/12/08 19:05
 */
public enum CompareStrategyEnum {

    /**
     * 单个儿属性比较，默认策略，会将比较前后的值存储到json
     */
    SINGLE_COMPARE,

    /**
     * 图片集合，存储修改前后的图片集合以单独属性存储到json
     */
    PICTURE_LIST,
    ;


}
