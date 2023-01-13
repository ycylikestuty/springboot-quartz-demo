package com.example.springbootquartzdemo.mapper;

import com.example.springbootquartzdemo.entity.Person;
import org.springframework.stereotype.Service;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ycy
 */
@Service("personMapper")
public interface PersonMapper {
    @Select("select id,name,age,sex,address,sect,skill,power,create_time createTime,modify_time modifyTime from quartz.persons")
    List<Person> queryList();
}
