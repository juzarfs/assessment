package com.assessment.webservice.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class DBConfig {
	@Value("${spring.datasource.driverClassName}")
    private String driverClassName;

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    

    @Value("${spring.datasource.username}")
    private String dbUser;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    
    @Bean
    public  DataSource h2DataSource() {
            
        DataSource datasource = new DataSource();
		datasource.setUrl(datasourceUrl);
		datasource.setUsername(dbUser);
		datasource.setPassword(dbPassword);
		datasource.setDriverClassName(driverClassName);
		return datasource;
    }
    
    @Bean
    public  NamedParameterJdbcTemplate h2NamedJdbcTemplate() {
        return new NamedParameterJdbcTemplate(h2DataSource());
    }
	
}
