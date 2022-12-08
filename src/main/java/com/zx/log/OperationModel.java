package com.zx.log;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.zx.enums.ResultCodeEnum;
import com.zx.exception.TmsServiceException;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 操作模型组合对象
 *
 * @author : zhangxuan
 * @date : 2022/12/08 19:05
 */
@Getter
public class OperationModel {

    // 修改前
    private final Map<Class<?>, Object> before = new HashMap<>();

    // 修改后
    private final Map<Class<?>, Object> after = new HashMap<>();

    // 修改前list
    private final Map<String, Collection<?>> beforeList = new HashMap<>();

    // 过滤出需要展示的集合字段，不支持单条数据的比较
    private final Map<String, List<String>> filterFields = new HashMap<>();

    // 修改后list
    private final Map<String, Collection<?>> afterList = new HashMap<>();

    // 标识符
    private String identifier;

    /**
     * 用于扩展的操作类型，如果model中设置了该值，则会将{@link OperationLog#typeName()}覆盖
     */
    @Setter
    private String typeName;

    // 业务扩展
    @Setter
    private String ext1;
    @Setter
    private String ext2;
    @Setter
    private String ext3;

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void addBefore(Object... before) {
        for (Object b : before) {
            this.before.put(b.getClass(), ObjectUtil.clone(b));
        }
    }

    public void addAfter(Object... after) {
        for (Object a : after) {
            this.after.put(a.getClass(), ObjectUtil.clone(a));
        }
    }

    public void addBeforeList(String bizType, Collection<?> datas) {
        validateKey(bizType);
        this.beforeList.put(bizType, datas);
    }

    public void addAfterList(String bizType, Collection<?> datas) {
        validateKey(bizType);
        this.afterList.put(bizType, datas);
    }

    public void addFilterField(String bizType, List<String> fileds) {
        validateKey(bizType);
        this.filterFields.put(bizType, fileds);
    }

    private void validateKey(String bizType) {
        if (StrUtil.isBlank(bizType)) {
            throw new TmsServiceException(ResultCodeEnum.PARAMS_IS_INVALID.getCode(), "bizType can not be null");
        }
    }
}
