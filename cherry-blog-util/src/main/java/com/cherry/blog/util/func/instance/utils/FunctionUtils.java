package com.cherry.blog.util.func.instance.utils;

/**
 * 自定义函数式接口
 *
 * @author weiliwang
 * @date 2023-6-18
 */
public class FunctionUtils {

    @FunctionalInterface
    public interface FunctionInterface1<A, R> {
        R function1(A a);

        default R preCheckFunction1(A a) {
            return a != null ? function1(a) : null;
        }
    }

    @FunctionalInterface
    public interface FunctionInterface2<A, B, R> {
        R function2(A a, B b);

        default R preCheckFunction2(A a, B b) {
            return (a != null && b != null) ? function2(a, b) : null;
        }
    }

    @FunctionalInterface
    public interface FunctionInterface3<A, B, C, R> {
        R function3(A a, B b, C c);

        default R preCheckFunction3(A a, B b, C c) {
            return (a != null && b != null && c != null) ? function3(a, b, c) : null;
        }
    }

    @FunctionalInterface
    public interface FunctionInterface4<A, B, C, D, R> {
        R function4(A a, B b, C c, D d);

        default R preCheckFunction4(A a, B b, C c, D d) {
            return (a != null && b != null && c != null && d != null) ? function4(a, b, c, d) : null;
        }
    }

    @FunctionalInterface
    public interface FunctionInterface5<A, B, C, D, E, R> {
        R function5(A a, B b, C c, D d, E e);

        default R preCheckFunction5(A a, B b, C c, D d, E e) {
            return (a != null && b != null && c != null && d != null && e != null) ? function5(a, b, c, d, e) : null;
        }
    }

    @FunctionalInterface
    public interface FunctionInterface6<A, B, C, D, E, F, R> {
        R function6(A a, B b, C c, D d, E e, F f);

        default R preCheckFunction6(A a, B b, C c, D d, E e, F f) {
            return (a != null && b != null && c != null && d != null && e != null && f != null) ? function6(a, b, c, d, e, f) : null;
        }
    }

}
