package com.cherry.blog.redis.enums;

import org.redisson.client.codec.Codec;

/**
 * @author weili.wang
 * @version 1.0
 * @date 2024/3/9 21:49
 */
public enum RedisCodeEnum {

    /**
     * json编码
     */
    JsonJacksonCodec(0, "JsonJacksonCodec", "json编码"),
    /**
     * String编码
     */
    StringCodec(1, "StringCodec", "String编码"),
    /**
     * byteArray编码
     */
    ByteArrayCodec(2, "ByteArrayCodec", "byteArray编码");

    /**
     * 编码类型
     */
    private int codeType;

    /**
     * 编码名称
     */
    private String codeName;

    /**
     * 编码描述
     */
    private String codeDesc;

    RedisCodeEnum(int codeType, String codeName, String codeDesc) {
        this.codeType = codeType;
        this.codeName = codeName;
        this.codeDesc = codeDesc;
    }

    /**
     * 获取编码名称
     * @param:
     * @return java.lang.String
     * @author weili.wang
     * @date 2024-03-09 21:57
     */
    public String getCodeName() {
        return this.codeName;
    }

    /**
     * 获取编码类型
     * @param:
     * @return int
     * @author weili.wang
     * @date 2024-03-09 21:57
     */
    public int getCodeType() {
        return this.codeType;
    }
}
