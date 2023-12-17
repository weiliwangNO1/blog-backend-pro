package com.cherry.blog.redis.model;

import lombok.*;

import java.io.Serializable;

/**
 * 测试数据
 * @author weiliwang
 * @date 2023/12/17
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private Integer id;

    private String name;

    private Integer age;

}
