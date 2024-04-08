package com.prowings.config;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
        return new Class[] { MyWebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
        return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {
		
		MyFilter myFilter = new MyFilter();

        return new Filter[]{myFilter};
	}

	
	
}
