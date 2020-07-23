package com.alibaba.controller;

import com.alibaba.mainmapper.MainEmployeeMapper;
import com.alibaba.minormapper.MinorEmployeeMapper;
import com.alibaba.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author shenmeng
 * @Date 2020/7/23
 **/
@RestController
@RequestMapping("/user")
public class EmployeeController {

    @Autowired
    private MainEmployeeMapper mainEmployeeMapper;

    @Autowired
    private MinorEmployeeMapper minorEmployeeMapper;

    @GetMapping("/find1")
    @ResponseBody
    public Employee queryEmployee(Integer id){
        Employee mainEmp = mainEmployeeMapper.getEmployeeById(id);
        return mainEmp;
    }

    @GetMapping("/find2")
    @ResponseBody
    public Employee queryEmployee2(Integer id){
        Employee mainEmp = minorEmployeeMapper.getEmployeeById(id);
        return mainEmp;
    }

}
