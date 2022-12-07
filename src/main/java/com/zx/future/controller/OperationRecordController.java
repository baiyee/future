package com.zx.future.controller;
import com.zx.future.model.OperationRecord;
import com.zx.future.service.OperationRecordServiceImpl;
import com.zx.future.service.OperationRecordServiceImplImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* (operation_record)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("/operation_record")
public class OperationRecordController {
/**
* 服务对象
*/
@Resource
private OperationRecordServiceImpl operationRecordServiceImpl;

/**
* 通过主键查询单条数据
*
* @param id 主键
* @return 单条数据
*/
@GetMapping("selectOne")
public OperationRecord selectOne(Integer id) {
return operationRecordServiceImpl.selectByPrimaryKey(id);
}

}
