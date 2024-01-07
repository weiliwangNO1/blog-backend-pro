package com.cherry.blog.util.enums;

/**
 * 结果返回状态编码
 * @author weili.wang
 * @date 2024/1/7
 */
public enum ResponseCodeEnum {

    /**
     * 执行成功
     */
    OK(200, "ok", "成功"),
    /**
     * 未授权操作
     */
    UNAUTHORIZED_OPERATION(401, "unauthorized.operation", "未授权操作"),
    /**
     * 后端错误
     */
    ERROR(500, "error", "错误"),
    /**
     * 执行失败
     */
    FAIL(10001, "fail", "执行失败");


    private Integer code;

    private String message;

    private String desc;

    ResponseCodeEnum(Integer code, String message, String desc) {
        this.code = code;
        this.message = message;
        this.desc = desc;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getDesc() {
        return this.desc;
    }
}
