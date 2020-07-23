package com.alibaba.mainmapper;

import com.alibaba.model.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Author shenmeng
 * @Date 2020/7/23
 **/
@Mapper
@Repository
public interface MainEmployeeMapper {

    @Select("select id,name,age from employee where id = #{id}")
    Employee getEmployeeById(Integer id);

}
