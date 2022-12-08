package com.zx.log;

import com.zx.model.UpsUser;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 操作模型集合
 *
 * @author : zhangxuan
 * @date : 2022/12/08 19:05
 */
public class OperationModels {

    @Getter
    @Setter
    private UpsUser user;

    @Getter
    private final List<OperationModel> models = new ArrayList<>();

}
