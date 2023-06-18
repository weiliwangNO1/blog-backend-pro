package com.cherry.blog.util.func.instance;

import com.cherry.blog.util.func.instance.utils.SupplierUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * supplier自定义函数式接口
 *
 * @author weiliwang
 * @date 2023-6-18
 */
public class SupplierInstance {

    public static <R> R supplier(SupplierUtils.SupplierInterface<R> supplierInterface) {
        return supplierInterface.supplier();
    }

    public static <R extends Integer> R supplierInteger(SupplierUtils.SupplierIntegerInterface<R> supplierIntegerInterface) {
        return supplierIntegerInterface.supplier();
    }

    public static <R extends String> R supplierStr(SupplierUtils.SupplierStrInterface<R> supplierStrInterface) {
        return supplierStrInterface.supplierStr();
    }

    public static <R extends List> R supplierList(SupplierUtils.SupplierListInterface<R> supplierListInterface) {
        return supplierListInterface.supplierList();
    }

    public static <R extends Set> R supplierSet(SupplierUtils.SupplierSetInterface<R> supplierSetInterface) {
        return supplierSetInterface.supplierSet();
    }

    public static <R extends Map> R supplierMap(SupplierUtils.SupplierMapInterface<R> supplierMapInterface) {
        return supplierMapInterface.supplierMap();
    }


}
