package com.cherry.blog.entities.test;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Builder
@Data
@ToString
public class Student implements Serializable {

    private Long studentId;

    private String name;

    private Integer age;

    private String className;

}
