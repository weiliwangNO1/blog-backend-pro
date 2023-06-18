package com.cherry.blog.entities.test;

import com.cherry.blog.entities.SysMenu;
import com.cherry.blog.util.func.instance.ConsumerInstance;
import com.cherry.blog.util.func.instance.FunctionInstance;
import com.cherry.blog.util.func.instance.PredicateInstance;
import com.cherry.blog.util.func.instance.SupplierInstance;
import com.cherry.blog.util.func.instance.utils.FunctionUtils;
import com.cherry.blog.util.func.instance.utils.SupplierUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TestDemo {


    public static void main(String[] args) {
        Student student1 = Student.builder()
                .studentId(1L)
                .name("junwu")
                .age(27)
                .className("computer")
                .build();
        Student student2 = Student.builder()
                .studentId(2L)
                .name("taoyang")
                .age(29)
                .className("math")
                .build();
        Teacher teacher = Teacher.builder()
                .teacherId(1L)
                .teacherName("weiliwang")
                .age(28)
                .className("computer")
                .studentList(Arrays.asList(student1, student2))
                .build();
        consumerTest(teacher);
        functionTest(teacher);
        predicateTest(teacher);
        supplierTest(teacher);

    }

    //查找junwu学生的信息
    public static void consumerTest(Teacher teacher) {
        ConsumerInstance.consumer1(teacher, t -> {
            System.out.println(t.getStudentList().stream()
            .filter(stu -> StringUtils.equalsIgnoreCase(stu.getName(), "junwu"))
            .findFirst().get());
        });
    }

    //查找junwu学生所在的学科
    public static void functionTest(Teacher teacher) {
        Optional<String> classNameOptional = FunctionInstance.function1(teacher, t -> {
            return t.getStudentList().stream()
                    .filter(stu -> StringUtils.equalsIgnoreCase(stu.getName(), "junwu"))
                    .findFirst()
                    .map(Student::getClassName);
        });
        classNameOptional.ifPresent(System.out::println);
    }

    //查找是否存在taoyang学生
    public static void predicateTest(Teacher teacher) {
        System.out.println(PredicateInstance.predicate1(teacher, t -> t.getStudentList().stream()
                .anyMatch(stu -> StringUtils.equalsIgnoreCase(stu.getName(), "taoyang"))));
    }

    //新增chengyang学生信息
    public static void supplierTest(Teacher teacher) {
        System.out.println(SupplierInstance.supplier(() -> Student.builder()
                .studentId(3L)
                .name("chengyang")
                .age(27)
                .className("math")
                .build()));
    }

}
