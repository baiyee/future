package com.zx.future.service;

import com.zx.future.model.OperationRecord;
public interface OperationRecordService{


    int deleteByPrimaryKey(Long id);

    int insert(OperationRecord record);

    int insertSelective(OperationRecord record);

    OperationRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OperationRecord record);

    int updateByPrimaryKey(OperationRecord record);

}
