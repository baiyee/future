package com.zx.future.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

/**
    * 操作记录表
    */
@ApiModel(value="操作记录表")
@Data
public class OperationRecord {
    /**
    * 主键
    */
    @ApiModelProperty(value="主键")
    private Long id;

    /**
    * 业务唯一标识符
    */
    @ApiModelProperty(value="业务唯一标识符")
    private String identifier;

    /**
    * 操作类型
    */
    @ApiModelProperty(value="操作类型")
    private String operationType;

    /**
    * 服务名
    */
    @ApiModelProperty(value="服务名")
    private String serviceName;

    /**
    * 操作者变更的内容
    */
    @ApiModelProperty(value="操作者变更的内容")
    private String content;

    /**
    * 更新者ID
    */
    @ApiModelProperty(value="更新者ID")
    private Integer updateBy;

    /**
    * 更新者名称
    */
    @ApiModelProperty(value="更新者名称")
    private String updateName;

    /**
    * 更新时间
    */
    @ApiModelProperty(value="更新时间")
    private Date updateTime;
}