package com.cherry.blog.util.func.instance.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 自定义函数式接口
 *
 * @author weiliwang
 * @date 2023-6-18
 */
public class SupplierUtils {

    @FunctionalInterface
    public interface SupplierInterface<R> {
        R supplier();
    }

    @FunctionalInterface
    public interface SupplierIntegerInterface<R extends Integer> {
        R supplier();
    }

    @FunctionalInterface
    public interface SupplierStrInterface<R extends String> {
        R supplierStr();
    }

    @FunctionalInterface
    public interface SupplierListInterface<R extends List> {
        R supplierList();
    }

    @FunctionalInterface
    public interface SupplierSetInterface<R extends Set> {
        R supplierSet();
    }

    @FunctionalInterface
    public interface SupplierMapInterface<R extends Map> {
        R supplierMap();
    }

}
