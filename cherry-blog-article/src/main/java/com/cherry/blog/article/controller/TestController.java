package com.cherry.blog.article.controller;

import java.util.Arrays;

public class TestController {


    public static void main(String[] args) {

        //1.二分查找算法
        System.out.println("二分查找算法位置：" + sort1(2));

        //2.冒泡算法1
        int[] arr = sort2();
        System.out.println("冒泡算法1---start");
        for (int value : arr) {
            System.out.print(value+ ",");
        }
        System.out.println("冒泡算法1---end");

        //3.改进冒泡算法
        int[] arr2 = sort3();
        System.out.println("冒泡算法2---start");
        for (int value : arr2) {
            System.out.print(value+ ",");
        }
        System.out.println("冒泡算法2---end");

        //4.插入算法算法
        int[] arr4 = sort4();
        System.out.println("插入算法---start");
        for (int value : arr4) {
            System.out.print(value+ ",");
        }
        System.out.println("插入算法---end");

    }

    //二分查找算法
    public static int sort1(int value) {
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        int start = 0;
        int end = arr.length -1;
        int mid;
        while(start <= end) {
            mid = (start + end) /2;
            if(arr[mid] == value) {
                return (mid +1);
            }else if(arr[mid] < value) {
                start = mid +1;
            }else {
                end = mid +1;
            }
        }
        return -1;
    }

    //冒泡算法1
    public static int[] sort2() {
        int[] arr = {3,1,5,10,6,2,9,20,11,19,30,21, 7, 14};
        for(int i = arr.length - 1; i>= 0; i--) {
            for(int j=i- 1; j >= 0 ; j--  ) {
                if(arr[i] < arr[j]) {
                    arr[i] = arr[i] + arr[j];
                    arr[j] = arr[i] - arr[j];  //得到arr[i]
                    arr[i] = arr[i] - arr[j];  //得到arr[j]
                    //int tmp = arr[i];
                   // arr[i] = arr[j];
                    //arr[j] = tmp;
                }
            }
        }
        return arr;
    }

    //改进冒泡算法
    public static int[] sort3() {
        int[] arr = {3,1,5,10,6,2,9,20,11,19,30,21, 7, 14};
        for(int i = arr.length - 1; i>= 0; i--) {
            boolean sortedTag = true;
            for(int j=i- 1; j >= 0 ; j--  ) {
                if(arr[i] < arr[j]) {
                    //int tmp = arr[i];
                    //arr[i] = arr[j];
                   // arr[j] = tmp;
                    arr[i] = arr[i] + arr[j];
                    arr[j] = arr[i] - arr[j];  //得到arr[i]
                    arr[i] = arr[i] - arr[j];  //得到arr[j]
                    sortedTag = false;
                }
            }
            if(sortedTag) {
                break;
            }
        }
        return arr;
    }

    //插入算法
    public static int[] sort4() {
        int[] arr = {1,2,3,4,8,9,10,12,16,20,26};
        for(int i = 1, length  = arr.length - 1; i <= length ; i++) {
            int beforeIndex = i -1;
            int value = arr[i];  //需要插入的值
            while(beforeIndex >= 0 && arr[i] < arr[beforeIndex]) {
                arr[beforeIndex + 1] = arr[beforeIndex];  //把前面的值挪到后一位
                beforeIndex --;
            }
            arr[beforeIndex + 1] = value;
         }
        return arr;
    }

}
