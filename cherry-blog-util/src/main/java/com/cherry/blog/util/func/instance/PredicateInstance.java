package com.cherry.blog.util.func.instance;

import com.cherry.blog.util.func.instance.utils.PredicateUtils;

/**
 * Predicate自定义函数式接口
 *
 * @author weiliwang
 * @date 2023-6-18
 */
public class PredicateInstance {

    public static <A> boolean predicate1(A a, PredicateUtils.PredicateInterface1<A> predicateInterface1) {
        return predicateInterface1.preCheckPredicate1(a);
    }

    public static <A, B> boolean predicate2(A a, B b, PredicateUtils.PredicateInterface2<A, B> predicateInterface2) {
        return predicateInterface2.preCheckPredicate2(a, b);
    }

    public static <A, B, C> boolean predicate3(A a, B b, C c, PredicateUtils.PredicateInterface3<A, B, C> predicateInterface3) {
        return predicateInterface3.preCheckPredicate3(a, b, c);
    }

    public static <A, B, C, D> boolean predicate4(A a, B b, C c, D d, PredicateUtils.PredicateInterface4<A, B, C, D> predicateInterface4) {
        return predicateInterface4.preCheckPredicate4(a, b, c, d);
    }

    public static <A, B, C, D, E> boolean predicate5(A a, B b, C c, D d, E e, PredicateUtils.PredicateInterface5<A, B, C, D, E> predicateInterface5) {
        return predicateInterface5.preCheckPredicate5(a, b, c, d, e);
    }

    public static <A, B, C, D, E, F> boolean predicate6(A a, B b, C c, D d, E e, F f, PredicateUtils.PredicateInterface6<A, B, C, D, E, F> predicateInterface6) {
        return predicateInterface6.preCheckPredicate6(a, b, c, d, e, f);
    }

}
