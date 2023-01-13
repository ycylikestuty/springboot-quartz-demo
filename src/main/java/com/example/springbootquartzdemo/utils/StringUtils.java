package com.example.springbootquartzdemo.utils;


/**
 * @author ycy
 * 字符串工具类
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
    /**
     * 空字符串
     */
    private static final String NULLSTR = "";


    /**
     * 判断一个对象是否为空
     *
     * @param object Object
     * @return boolean
     */
    public static boolean isNull(Object object) {
        return object == null;
    }


}
