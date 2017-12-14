package com.alvin.common.result;

import lombok.Data;

@Data
public class Result {
    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result() {
    }

    private Integer code;
    private String message;

    public static Result getSuccessInstants() {
        return new Result(ResultCode.SUCCESS_CODE, ResultCode.SUCCESS_MSG);
    }

    public static Result getNotFountInstants() {
        return new Result(ResultCode.NOT_FOUNT_CODE, ResultCode.NOT_FOUNT_MSG);
    }
}
