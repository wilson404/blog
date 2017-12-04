package com.wilson404.blog.common;

public enum ResponseCode {
    SUCCESS(0, "SUCCESS"),//成功
    ERROR(1, "ERROR"),//错误
    NEED_LOGIN(10, "NEED_LOGIN"),//需要登录
    PASSWORD_ERROR(11,"PASSWORD_ERROR"),//密码错误
    ILLEGAL_ARGUMENT(2, "ILLEGAL_ARGUMENT");//非法参数
    private int code;
    private String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
