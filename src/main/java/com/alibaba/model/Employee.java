package com.alibaba.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author shenmeng
 * @Date 2020/7/23
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private Integer id;

    private String name;

    private Integer age;

}
