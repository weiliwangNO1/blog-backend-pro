package com.cherry.blog.util.func.instance;

import com.cherry.blog.util.func.instance.utils.ConsumerUtils;

/**
 * consumer自定义函数式接口
 *
 * @author weiliwang
 * @date 2023-6-18
 */
public class ConsumerInstance {

    public static <A> void consumer1(A a, ConsumerUtils.ConsumerInterface1<A> consumerInterface1) {
        consumerInterface1.preCheckConsumer1(a);
    }

    public static <A, B> void consumer2(A a, B b, ConsumerUtils.ConsumerInterface2<A, B> consumerInterface2) {
        consumerInterface2.preCheckConsumer2(a, b);
    }

    public static <A, B, C> void consumer3(A a, B b, C c, ConsumerUtils.ConsumerInterface3<A, B, C> consumerInterface3) {
        consumerInterface3.preCheckConsumer3(a, b, c);
    }

    public static <A, B, C, D> void consumer4(A a, B b, C c, D d, ConsumerUtils.ConsumerInterface4<A, B, C, D> consumerInterface4) {
        consumerInterface4.preCheckConsumer4(a, b, c, d);
    }

    public static <A, B, C, D, E> void consumer5(A a, B b, C c, D d, E e, ConsumerUtils.ConsumerInterface5<A, B, C, D, E> consumerInterface5) {
        consumerInterface5.preCheckConsumer5(a, b, c, d, e);
    }

    public static <A, B, C, D, E, F> void consumer6(A a, B b, C c, D d, E e, F f, ConsumerUtils.ConsumerInterface6<A, B, C, D, E, F> consumerInterface6) {
        consumerInterface6.preCheckConsumer5(a, b, c, d, e, f);
    }

}
