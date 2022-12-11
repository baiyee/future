package com.zx.log;

import com.zx.model.UpsUser;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 抽象的日志组合对象
 *
 * @author zhangxuan
 * @date 2022/12/08 21:16
 */
public abstract class AbstractLogComposite<T> {

    @Getter
    private OperationModel model;

    @Getter
    private final UpsUser user;

    protected AbstractLogComposite(UpsUser user) {
        this.user = user;
    }

    protected void beforeLog(T data) {
        model = new OperationModel();
        OperationContext.addUser(user);
        OperationContext.addModel(model);
        model.addBefore(data);
    }

    protected void afterLog(T data, String identifier) {
        if (model != null) {
            model.addAfter(data);
            model.setIdentifier(identifier);
        }
    }

    protected void addExt1(String ext1) {
        if (model != null) {
            model.setExt1(ext1);
        }
    }

    protected void addExt2(String ext2) {
        if (model != null) {
            model.setExt2(ext2);
        }
    }

    protected void addExt3(String ext3) {
        if (model != null) {
            model.setExt3(ext3);
        }
    }



}
