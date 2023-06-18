package com.cherry.blog.util.func.instance.utils;

/**
 * 自定义函数式接口
 *
 * @author weiliwang
 * @date 2023-6-18
 */
public class ConsumerUtils {

    @FunctionalInterface
    public interface ConsumerInterface1<A> {
        void consumer1(A a);

        default void preCheckConsumer1(A a) {
            if (a != null) {
                consumer1(a);
            }
        }
    }

    @FunctionalInterface
    public interface ConsumerInterface2<A, B> {
        void consumer2(A a, B b);

        default void preCheckConsumer2(A a, B b) {
            if (a != null && b != null) {
                consumer2(a, b);
            }
        }
    }

    @FunctionalInterface
    public interface ConsumerInterface3<A, B, C> {
        void consumer3(A a, B b, C c);

        default void preCheckConsumer3(A a, B b, C c) {
            if (a != null && b != null && c != null) {
                consumer3(a, b, c);
            }
        }
    }

    @FunctionalInterface
    public interface ConsumerInterface4<A, B, C, D> {
        void consumer4(A a, B b, C c, D d);

        default void preCheckConsumer4(A a, B b, C c, D d) {
            if (a != null && b != null && c != null && d != null) {
                consumer4(a, b, c, d);
            }
        }
    }

    @FunctionalInterface
    public interface ConsumerInterface5<A, B, C, D, E> {
        void consumer5(A a, B b, C c, D d, E e);

        default void preCheckConsumer5(A a, B b, C c, D d, E e) {
            if (a != null && b != null && c != null && d != null && e != null) {
                consumer5(a, b, c, d, e);
            }
        }
    }

    @FunctionalInterface
    public interface ConsumerInterface6<A, B, C, D, E, F> {
        void consumer6(A a, B b, C c, D d, E e, F f);

        default void preCheckConsumer5(A a, B b, C c, D d, E e, F f) {
            if (a != null && b != null && c != null && d != null && e != null && f != null) {
                consumer6(a, b, c, d, e, f);
            }
        }
    }

}
