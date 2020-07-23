package com.alibaba.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @Author shenmeng
 * @Date 2020/7/23
 **/
@Configuration
@MapperScan(basePackages = "com.alibaba.minormapper",sqlSessionTemplateRef = "minorSqlSessionTemplate")
public class MinorDataSourceConfig {

    /**
     * 数据源
     * @return
     */
    @Bean("minorDataSource")
    public DataSource buildDataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://mysqlos1:3306/springdb?useUnicode=true&characterEncoding=utf-8");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("root");
        return druidDataSource;
    }

    /**
     * sqlSessionFactroy
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean("minorSqlSessionFactory")
    public SqlSessionFactory buildSqlSessionFactory(
            @Qualifier("minorDataSource") DataSource dataSource) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 事物管理器
     * @param dataSource
     * @return
     */
    @Bean("minorTransactionManager")
    public DataSourceTransactionManager buildTransactionManager(@Qualifier("minorDataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * SqlSessionTemplate
     * @param sqlSessionFactory
     * @return
     */
    @Bean("minorSqlSessionTemplate")
    public SqlSessionTemplate buildSqlSessionTemplate(@Qualifier("minorSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    
}
