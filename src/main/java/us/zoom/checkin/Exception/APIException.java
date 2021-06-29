package us.zoom.checkin.Exception;

import lombok.Getter;
import us.zoom.checkin.common.ResultCode;

@Getter //只要getter方法，无需setter
public class APIException extends RuntimeException {
    private int code;
    private String msg;

    public APIException() {
        this(ResultCode.FAILED.getCode(), "接口错误");
    }

    public APIException(String msg) {
        this(ResultCode.FAILED.getCode(), msg);
    }

    public APIException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}