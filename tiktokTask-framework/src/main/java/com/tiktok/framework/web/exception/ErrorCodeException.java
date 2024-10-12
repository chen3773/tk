package com.tiktok.framework.web.exception;

import com.tiktok.framework.web.domain.ResultCode;

public class ErrorCodeException extends RuntimeException{

    private Integer code;
    private String message;

    public ErrorCodeException(ResultCode resultCode, String msg){
        this.code=resultCode.getCode();
        this.message=msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
