package com.zx.log;

import cn.hutool.core.util.StrUtil;
import com.zx.model.UpsUser;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 操作日志处理结果
 *
 * @author : zhangxuan
 * @date : 2022/12/08 19:05
 */
public class OperationResult {

    @Getter
    @Setter
    private String operation;

    @Getter
    private Long updateBy;

    @Getter
    private String updateName;

    @Getter
    private LocalDateTime updateTime;

    @Getter
    private String identifier;

    @Getter
    @Setter
    private String serviceName;

    @Getter
    private ComparatorResult picture;

    @Getter
    private String ext1;

    @Getter
    private String ext2;

    @Getter
    private String ext3;

    @Getter
    private final List<ComparatorResult> comparators = new ArrayList<>();

    /**
     * 添加差异结果
     *
     * @param fieldName   字段名
     * @param displayName 字段展示名
     * @param beforeValue 操作前数据
     * @param afterValue  操作后数据
     */
    public void addDifference(String fieldName, String displayName, Object beforeValue, Object afterValue) {
        comparators.add(new ComparatorResult(fieldName, displayName, beforeValue, afterValue));
    }

    /**
     * 添加图片的前后结果
     *
     * @param fieldName   字段名
     * @param displayName 字段展示名
     * @param beforeValue 操作前数据
     * @param afterValue  操作后数据
     */
    public void addPicture(String fieldName, String displayName, Object beforeValue, Object afterValue) {
        this.picture = new ComparatorResult(fieldName, displayName, beforeValue, afterValue);
    }

    /**
     * 将操作的用户添加值操作结果中
     */
    public void addUser() {
        UpsUser user = OperationContext.getModels().getUser();

        if (user != null) {
            this.updateBy = user.getId();
            this.updateName = user.getName();
            this.updateTime = LocalDateTime.now();
        }
    }

    public void addOperation(OperationLog log) {
        if (StrUtil.isBlank(this.operation)) {
            this.operation = log.typeName();
        }
        this.serviceName = log.serviceName();
    }

    public void addIdentifier(String identifier) {
        this.identifier = identifier;
    }


    public void addExts(String... args) {
        if (args != null && args.length > 0) {
            if (StrUtil.isNotBlank(args[0])) {
                this.ext1 = args[0];
            }
            if (StrUtil.isNotBlank(args[1])) {
                this.ext2 = args[1];
            }
            if (StrUtil.isNotBlank(args[2])) {
                this.ext3 = args[2];
            }
        }
    }
}
