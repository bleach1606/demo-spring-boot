package com.example.demo.config;

import com.example.demo.config.tracing.CustomURLFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class RequestLoggingFilterConfig {

	@Bean
	public CommonsRequestLoggingFilter logFilter() {
		CommonsRequestLoggingFilter filter
						= new CommonsRequestLoggingFilter();
		filter.setIncludeQueryString(true);
		filter.setIncludePayload(true);
		filter.setMaxPayloadLength(10240);
		filter.setIncludeHeaders(false);
		return filter;
	}

	@Bean
	public FilterRegistrationBean <CustomURLFilter> filterRegistrationBean() {
		FilterRegistrationBean<CustomURLFilter> registrationBean = new FilterRegistrationBean();
		CustomURLFilter customURLFilter = new CustomURLFilter();
		registrationBean.setFilter(customURLFilter);
		registrationBean.setOrder(2); //set precedence
		return registrationBean;
	}
}
