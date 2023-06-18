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

    public interface SupplierInterface<R> {
        R supplier();
    }

    public interface SupplierIntegerInterface<R extends Integer> {
        R supplier();
    }

    public interface SupplierStrInterface<R extends String> {
        R supplierStr();
    }

    public interface SupplierListInterface<R extends List> {
        R supplierList();
    }

    public interface SupplierSetInterface<R extends Set> {
        R supplierSet();
    }

    public interface SupplierMapInterface<R extends Map> {
        R supplierMap();
    }

}
