package com.zx.log;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;

/**
 * 操作结果
 *
 * @author : zhangxuan
 * @date : 2022/12/08 19:05
 */
@Getter
public class ComparatorResult {

    private final String field;

    private final String displayName;

    private final Object beforeValue;

    private final Object afterValue;

    private final String type;

    public ComparatorResult(String field, String displayName, Object beforeValue, Object afterValue) {
        if (StrUtil.isBlank(field)) {
            type = "list";
        } else {
            type = "obj";
        }
        this.field = field;
        this.displayName = displayName;
        this.beforeValue = beforeValue;
        this.afterValue = afterValue;
    }
}
