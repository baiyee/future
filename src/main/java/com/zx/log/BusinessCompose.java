package com.zx.log;

import com.zx.model.OperationRecord;
import com.zx.vo.OperationRecordVO;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 业务装饰者类
 *
 * @author wangshengquan
 * @date 2022/11/29 13:43
 */
@Slf4j
public class BusinessCompose extends AbstractLogComposite<OperationRecord> {

    /**
     * 日志对象
     */
    @Getter
    private OperationRecordVO vo;

    /**
     * 异常上报
     */
    @Getter
    private final List<OperationRecord> exceptionReports = new ArrayList<>();

    /**
     * 日志返回结果
     */
    @Getter
    private OperationRecord logResult;


    public BusinessCompose(OperationRecordVO vo) {
        super(vo.getUser());
        this.vo = vo;
    }

    public void exec() {
        vo.addOperation(getUser());
        this.beforeLog(vo);
        vo.setPicture("http://img.daimg.com/uploads/allimg/220407/3-22040H31Z0.jpg");
        this.afterLog(vo,vo.getIdentifier());
    }







}
