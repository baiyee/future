package com.zx.controller;

import com.zx.model.OperationRecord;
import com.zx.service.OperationRecordService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 操作记录控制层
 *
 * @author zhangxuan
 */
@RestController
@RequestMapping("/operationRecord")
public class OperationRecordController {
    /**
     * 服务对象
     */
    @Resource
    private OperationRecordService operationRecordService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public OperationRecord selectOne(Long id) {
        return operationRecordService.selectByPrimaryKey(id);
    }

}
