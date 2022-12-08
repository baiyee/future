package com.zx.exception;

import com.zx.enums.ResultCodeEnum;
import lombok.Data;

import java.util.List;

/**
 * @description: 自定义异常
 * @author: linhaibo
 * @since: 2019-05-20
 */
@Data
public class TmsServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private Integer code;

    private List<String> details;

    public TmsServiceException(ResultCodeEnum resultCode){
        super(resultCode.getMsg());
        this.code = resultCode.getCode();
    }
    public TmsServiceException(RespCodeEnum resultCode){
        super(resultCode.getMsg());
        this.code = resultCode.getCode();
    }


    public TmsServiceException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public TmsServiceException(Integer code, String msg, List<String> details) {
        super(msg);
        this.code = code;
        this.details = details;
    }


    public TmsServiceException(String msg) {
        super(msg);
    }

    public TmsServiceException(Throwable throwable) {
        super(throwable);
    }

    public TmsServiceException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

}
