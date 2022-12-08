package com.zx.log;

import java.util.List;

/**
 * 数据比较器
 *
 * @author : zhangxuan
 * @date : 2022/12/08 19:05
 */
public interface DataComparator {

    /**
     * 比较生成操作结果
     *
     * @return 操作结果
     */
    List<OperationResult> comparator();
}
