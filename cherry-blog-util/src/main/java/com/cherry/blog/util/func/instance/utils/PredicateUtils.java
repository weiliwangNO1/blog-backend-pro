package com.cherry.blog.util.func.instance.utils;

/**
 * 自定义函数式接口
 *
 * @author weiliwang
 * @date 2023-6-18
 */
public class PredicateUtils {

    @FunctionalInterface
    public interface PredicateInterface1<A> {
        boolean predicate1(A a);

        default boolean preCheckPredicate1(A a) {
            return a != null && predicate1(a);
        }
    }

    @FunctionalInterface
    public interface PredicateInterface2<A, B> {
        boolean predicate2(A a, B b);

        default boolean preCheckPredicate2(A a, B b) {
            return a != null && b != null && predicate2(a, b);
        }
    }

    @FunctionalInterface
    public interface PredicateInterface3<A, B, C> {
        boolean predicate3(A a, B b, C c);

        default boolean preCheckPredicate3(A a, B b, C c) {
            return a != null && b != null && c != null && predicate3(a, b, c);
        }
    }

    @FunctionalInterface
    public interface PredicateInterface4<A, B, C, D> {
        boolean predicate4(A a, B b, C c, D d);

        default boolean preCheckPredicate4(A a, B b, C c, D d) {
            return a != null && b != null && c != null && d != null && predicate4(a, b, c, d);
        }
    }

    @FunctionalInterface
    public interface PredicateInterface5<A, B, C, D, E> {
        boolean predicate5(A a, B b, C c, D d, E e);

        default boolean preCheckPredicate5(A a, B b, C c, D d, E e) {
            return a != null && b != null && c != null && d != null && e != null && predicate5(a, b, c, d, e);
        }
    }

    @FunctionalInterface
    public interface PredicateInterface6<A, B, C, D, E, F> {
        boolean predicate6(A a, B b, C c, D d, E e, F f);

        default boolean preCheckPredicate6(A a, B b, C c, D d, E e, F f) {
            return a != null && b != null && c != null && d != null && e != null && f != null && predicate6(a, b, c, d, e, f);
        }
    }

}
