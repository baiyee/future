package com.zx.log;


import com.zx.model.UpsUser;

/**
 * 操作上下文
 *
 * @author : zhangxuan
 * @date : 2022/12/08 19:05
 */
public class OperationContext {

    private static final ThreadLocal<OperationModels> MODEL_CONTEXT = ThreadLocal.withInitial(OperationModels::new);

    /**
     * 添加一个操作模型
     * @param model 比较模型
     */
    public static void addModel(OperationModel model) {
        MODEL_CONTEXT.get().getModels().add(model);
    }

    /**
     * 获取操作模型
     *
     * @return 操作模型封装对象
     */
    public static OperationModels getModels() {
        return MODEL_CONTEXT.get();
    }

    /**
     * 清空上下文
     */
    public static void clean() {
        MODEL_CONTEXT.remove();
    }

    /**
     * 添加用户
     *
     * @param upsUser 用户
     */
    public static void addUser(UpsUser upsUser) {
        MODEL_CONTEXT.get().setUser(upsUser);
    }

}
