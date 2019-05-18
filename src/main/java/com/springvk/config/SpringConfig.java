package com.springvk.config;

import com.springvk.service.TestBean;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Objects;

@Configuration
@ComponentScan(basePackages = {"com.springvk.service", "com.springvk.dao"})
@PropertySource("classpath:database.properties")
public class SpringConfig {

    @Autowired
    private Environment properties;

    @Bean
    public TestBean getTestBean(){
        return new TestBean("hello");
    }

    @Bean
    public JdbcTemplate getJdbcTemplate(){
        return new JdbcTemplate(getDataSource());
    }

    @Bean(value = "dataSource", destroyMethod = "close")
    @Scope("singleton")
    public BasicDataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(properties.getProperty("driverName"));
        dataSource.setPassword(properties.getProperty("password"));
        dataSource.setUrl(properties.getProperty("url"));
        dataSource.setInitialSize(Integer.valueOf(Objects.requireNonNull(properties.getProperty("initialSize"))));
        dataSource.setUsername(properties.getProperty("login"));
        dataSource.setMaxActive(Integer.valueOf(Objects.requireNonNull(properties.getProperty("maxActive"))));
        return dataSource;
    }

//    @Bean
//    public UserDao getUserDao(){
//        return new UserDaoImpl(getJdbcTemplate());
//    }
//
//    @Bean
//    public UserService getUserService(){
//        return new UserServiceImpl();
//    }
}
