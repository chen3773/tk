package com.tiktok.framework.web.domain;

public enum ResultCode {
    SUCCESS(200),//成功
    FAIL(400),//失败
    FAIL_SIGN_IN(401),//登录失败
    TOKEN_OVERDUE(402),//token过期
    NOT_FOUND(404),//接口不存在
    INTERNAL_SERVICE_ERROR(500),//服务器内部错误
    CUSTOMER_SERVICE_ERROR(505);//服务器内部错误

    private final Integer code;

    ResultCode(Integer code){
        this.code=code;
    }

    private Integer code(){
        return code;
    }

    public Integer getCode() {
        return code;
    }
}

