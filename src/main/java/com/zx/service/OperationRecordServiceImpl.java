package com.zx.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.zx.model.OperationRecord;
import com.zx.mapper.OperationRecordMapper;

@Service
public class OperationRecordServiceImpl implements OperationRecordService{

    @Resource
    private OperationRecordMapper operationRecordMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return operationRecordMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(OperationRecord record) {
        return operationRecordMapper.insert(record);
    }

    @Override
    public int insertSelective(OperationRecord record) {
        return operationRecordMapper.insertSelective(record);
    }

    @Override
    public OperationRecord selectByPrimaryKey(Long id) {
        return operationRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(OperationRecord record) {
        return operationRecordMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(OperationRecord record) {
        return operationRecordMapper.updateByPrimaryKey(record);
    }

}
