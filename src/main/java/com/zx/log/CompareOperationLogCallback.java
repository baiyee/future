package com.zx.log;

import cn.hutool.core.collection.CollUtil;
import com.zx.mapper.OperationRecordMapper;
import com.zx.model.OperationRecord;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 比较操作日志回调
 *
 * @author : zhangxuan
 * @date : 2022/12/08 19:05
 */
@Component
public class CompareOperationLogCallback implements OperationLogCallback {

    @Resource
    private OperationRecordMapper recordMapper;

    @Override
    public void callback(List<OperationResult> results) {
        for (OperationResult result : results) {

            if (CollUtil.isNotEmpty(result.getComparators())) {

                OperationRecord record = new OperationRecord();

                record.addResult(result);

                recordMapper.insert(record);
            }
        }

    }
}
