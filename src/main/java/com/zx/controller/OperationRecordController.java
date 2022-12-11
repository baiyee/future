package com.zx.controller;

import com.zx.log.OperationLog;
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
    @OperationLog(typeName = "查询单个日志", serviceName = "OperationRecordController")
    @GetMapping("selectOne")
    public OperationRecord selectOne(Long id) {
        return operationRecordService.selectByPrimaryKey(id);
    }


    /**
     * 通过主键删除单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @OperationLog(typeName = "删除单个日志", serviceName = "OperationRecordController")
    @GetMapping("delete")
    public int delete(Long id) {
        int i = operationRecordService.deleteByPrimaryKey(id);
        return i;
    }
}
