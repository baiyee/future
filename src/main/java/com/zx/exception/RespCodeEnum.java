package com.zx.exception;

/**
 * @author zhangxuan
 */
public interface RespCodeEnum {
    /**
     * 获取code
     * @return
     */
    int getCode();

    /**
     * 获取key
     * @return
     */
    String getKey();

    /**
     * 获取msg
     * @return
     */
    String getMsg();

    /**
     * 设置形参
     * @return
     */
    void setArgs(Object[] var1);
}