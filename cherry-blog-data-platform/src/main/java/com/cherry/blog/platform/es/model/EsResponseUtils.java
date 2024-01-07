package com.cherry.blog.platform.es.model;

/**
 * es返回结果
 * @author weili.wang
 * @date 2024/1/7
 */
public class EsResponseUtils<T> {

    private boolean success;

    private Integer code;

    private T data;

    /**
     * 构建结果
     * @return
     */
    public static EsResponseUtils builder() {
        return new EsResponseUtils<>();
    }

    /**
     * 设置success
     * @param success
     * @return
     */
    public EsResponseUtils success(boolean success) {
        this.success = success;
        return this;
    }

    /**
     * 设置code
     * @param code
     * @return
     */
    public EsResponseUtils code(Integer code) {
        this.code = code;
        return this;
    }

    /**
     * 设置data
     * @param data
     * @return
     */
    public EsResponseUtils data(T data) {
        this.data = data;
        return this;
    }

}
