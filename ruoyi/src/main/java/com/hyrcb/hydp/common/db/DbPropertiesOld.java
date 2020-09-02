package com.hyrcb.hydp.common.db;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 数据库的配置类
 * @author cc
 * @date 2020-09-02
 * @version 1.0
 */
@Configuration
@ConfigurationProperties(prefix = "spring.datasource.dynamic.datasource.old")
public class DbPropertiesOld {

    //	@Value("${spring.datasource.dynamic.datasource.slave_db2_101.username}")
    private String username;

    //	@Value("${spring.datasource.dynamic.datasource.slave_db2_101.password}")
    private String password;

    //	@Value("${spring.datasource.dynamic.datasource.slave_db2_101.driver-class-name}")
    private String driverClassName;

    //	@Value("${spring.datasource.dynamic.datasource.slave_db2_101.url}")
    private String url;

    public DbPropertiesOld() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
