package com.zx.model;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.zx.log.OperationField;
import com.zx.log.OperationResult;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 *
 * @author zhangxuan
 * @date 2022/12/7 23:51
 */
@Data
@TableName("OPERATION_RECORD")
@Schema(name = "操作记录对象")
public class OperationRecord extends Model<OperationRecord> {
    /**
     * id
     */
    @TableId(value = "ID", type = IdType.INPUT)
    private Integer id;

    /**
     * 业务标识符
     */
    @TableField("IDENTIFIER")
    private String identifier;

    /**
     * 操作类型
     */
    @TableField("OPERATION_TYPE")
    private String operationType;

    /**
     * 服务名
     */
    @TableField("SERVICE_NAME")
    private String serviceName;

    /**
     * 操作变动的数据，包括修改前和修改后
     */
    @TableField("CONTENT")
    private String content;

    /**
     * 图片，是一个json
     */
    @TableField("PICTURE")
    @OperationField(displayName = "图片地址")
    private String picture;

    /**
     * 更新者ID
     */
    @TableField("UPDATE_BY")
    private Long updateBy;

    /**
     * 更新人
     */
    @TableField("UPDATE_NAME")
    private String updateName;

    /**
     * 操作时间
     */
    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;

    /**
     * 业务扩展1
     */
    @TableField("EXT1")
    private String ext1;


    /**
     * 业务扩展2
     */
    @TableField("EXT2")
    private String ext2;

    /**
     * 业务扩展3
     */
    @TableField("EXT3")
    private String ext3;


    /**
     * 添加结果记录
     *
     * @param result 处理结果
     */
    public void addResult(OperationResult result) {
        this.content = JSONUtil.toJsonStr(result.getComparators());
        this.picture = JSONUtil.toJsonStr(result.getPicture());
        this.serviceName = result.getServiceName();
        this.operationType = result.getOperation();
        this.identifier = result.getIdentifier();
        this.updateName = result.getUpdateName();
        this.updateBy = result.getUpdateBy();
        this.updateTime = result.getUpdateTime();
        this.ext1 = result.getExt1();
        this.ext2 = result.getExt2();
        this.ext3 = result.getExt3();
    }


    /**
     * 添加用户信息
     *
     * @param user 用户信息
     */
    public void addOperation(UpsUser user) {
        if (user != null) {
            this.updateBy = user.getId() == null ? null : user.getId();
            this.updateName = user.getName();
        }
        this.updateTime = LocalDateTime.now();
    }
}