package com.cherry.blog.util.func.instance;

import com.cherry.blog.util.func.instance.utils.FunctionUtils;

/**
 * function自定义函数式接口
 *
 * @author weiliwang
 * @date 2023-6-18
 */
public class FunctionInstance {

    public static <A, R> R function1(A a, FunctionUtils.FunctionInterface1<A, R> functionInterface1) {
        return functionInterface1.preCheckFunction1(a);
    }

    public static <A, B, R> R function2(A a, B b, FunctionUtils.FunctionInterface2<A, B, R> functionInterface2) {
        return functionInterface2.preCheckFunction2(a, b);
    }

    public static <A, B, C, R> R function3(A a, B b, C c, FunctionUtils.FunctionInterface3<A, B, C, R> functionInterface3) {
        return functionInterface3.preCheckFunction3(a, b, c);
    }

    public static <A, B, C, D, R> R function4(A a, B b, C c, D d, FunctionUtils.FunctionInterface4<A, B, C, D, R> functionInterface4) {
        return functionInterface4.preCheckFunction4(a, b, c, d);
    }

    public static <A, B, C, D, E, R> R function5(A a, B b, C c, D d, E e, FunctionUtils.FunctionInterface5<A, B, C, D, E, R> functionInterface5) {
        return functionInterface5.preCheckFunction5(a, b, c, d, e);
    }

    public static <A, B, C, D, E, F, R> R function6(A a, B b, C c, D d, E e, F f, FunctionUtils.FunctionInterface6<A, B, C, D, E, F, R> functionInterface6) {
        return functionInterface6.preCheckFunction6(a, b, c, d, e, f);
    }
}
