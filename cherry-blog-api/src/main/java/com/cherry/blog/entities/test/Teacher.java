package com.cherry.blog.entities.test;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Builder
@Data
@ToString
public class Teacher implements Serializable {

    private Long teacherId;

    private String teacherName;

    private Integer age;

    private String className;

    private List<Student> studentList;

}
