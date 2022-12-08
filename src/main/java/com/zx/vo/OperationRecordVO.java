package com.zx.vo;

import com.zx.model.OperationRecord;
import com.zx.model.UpsUser;
import lombok.Data;

/**
 * 操作记录
 *
 * @author theshy
 * @date 2022年12月08日 23:32
 */
@Data
public class OperationRecordVO extends OperationRecord {

    UpsUser user;
}
