package com.zx.service;

import com.zx.config.orika.OrikaBeanMapper;
import com.zx.log.BusinessCompose;
import com.zx.model.UpsUser;
import com.zx.vo.OperationRecordVO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.zx.model.OperationRecord;
import com.zx.mapper.OperationRecordMapper;

@Service
public class OperationRecordServiceImpl implements OperationRecordService{

    @Resource
    private OperationRecordMapper operationRecordMapper;

    @Resource
    private OrikaBeanMapper orikaBeanMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        OperationRecord operationRecord = operationRecordMapper.selectByPrimaryKey(id);
        OperationRecordVO vo = orikaBeanMapper.map(operationRecord, OperationRecordVO.class);
        BusinessCompose businessCompose = new BusinessCompose(vo);
        businessCompose.exec();
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
        OperationRecord operationRecord = operationRecordMapper.selectByPrimaryKey(id);
        OperationRecordVO vo = orikaBeanMapper.map(operationRecord, OperationRecordVO.class);
        vo.setUser(new UpsUser().setId(1L).setName("admin"));
        BusinessCompose businessCompose = new BusinessCompose(vo);
        businessCompose.exec();
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
