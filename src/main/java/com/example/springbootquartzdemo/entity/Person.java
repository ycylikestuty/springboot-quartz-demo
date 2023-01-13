package com.example.springbootquartzdemo.entity;

import lombok.Data;

/**
 * @author ycy
 */
@Data
public class Person {
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     */
    private String sex;

    /**
     * 住址
     */
    private String address;

    /**
     * 门派
     */
    private String sect;

    /**
     * 绝技
     */
    private String skill;

    /**
     * 战斗值
     */
    private Integer power;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;
}
