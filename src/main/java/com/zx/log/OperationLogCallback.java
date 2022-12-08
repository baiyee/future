package com.zx.log;

import java.util.List;

/**
 * 操作日志回调接口
 *
 * @author : zhangxuan
 * @date : 2022/12/08 19:05
 */
public interface OperationLogCallback {

    /**
     * 回调，可实现自定义日志处理
     *
     * @param result 抽象处理将结果
     */
    void callback(List<OperationResult> result);
}
