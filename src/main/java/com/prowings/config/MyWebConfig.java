package com.prowings.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import com.prowings.exception.StudentExceptionHandler;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.prowings")
public class MyWebConfig implements WebMvcConfigurer{

	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/student-rest-db");
		ds.setUsername("prowingsuser");
		ds.setPassword("prowingsuser");
		return ds;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

		sessionFactory.setDataSource(dataSource()); // manually set DS to SF
		sessionFactory.setPackagesToScan("com.prowings.entity");
		sessionFactory.setHibernateProperties(readHibernateProps()); // set hibernate prop

		return sessionFactory;
	}

	private Properties readHibernateProps() {
		Properties props = new Properties();
		props.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
		props.put("hibernate.show_sql", "true");
		props.put("hibernate.format_sql", "true");
		props.put("hibernate.hbm2ddl.auto", "update");
		return props;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
       // Register interceptor for specific controller method
        registry.addInterceptor(new LoggingInterceptor())
                .addPathPatterns("/v1/*"); 
	}

	
	

}
