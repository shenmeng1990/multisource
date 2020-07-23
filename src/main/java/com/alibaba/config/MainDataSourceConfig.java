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
@MapperScan(basePackages = "com.alibaba.mainmapper",sqlSessionTemplateRef = "mainSqlSessionTemplate")
public class MainDataSourceConfig {

    /**
     * 数据源
     * @return
     */
    @Bean("mainDataSource")
    @Primary
    public DataSource buildDataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://mysqlos:3306/springdb?useUnicode=true&characterEncoding=utf-8");
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
    @Bean("mainSqlSessionFactory")
    @Primary
    public SqlSessionFactory buildSqlSessionFactory(
            @Qualifier("mainDataSource") DataSource dataSource) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 事物管理器
     * @param dataSource
     * @return
     */
    @Bean("mainTransactionManager")
    @Primary
    public DataSourceTransactionManager buildTransactionManager(@Qualifier("mainDataSource") DataSource dataSource){
       return new DataSourceTransactionManager(dataSource);
    }

    /**
     * SqlSessionTemplate
     * @param sqlSessionFactory
     * @return
     */
    @Bean("mainSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate buildSqlSessionTemplate(@Qualifier("mainSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
