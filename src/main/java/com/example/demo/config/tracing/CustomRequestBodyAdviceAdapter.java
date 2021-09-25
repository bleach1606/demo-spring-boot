package com.example.demo.config.tracing;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;

@Slf4j
@ControllerAdvice
public class CustomRequestBodyAdviceAdapter extends RequestBodyAdviceAdapter {

	@Autowired
	HttpServletRequest httpServletRequest;

	@Override
	public boolean supports(MethodParameter methodParameter, Type type,
	                        Class<? extends HttpMessageConverter<?>> aClass) {
		return true;
	}

	@Override
	public Object afterBodyRead(Object body, HttpInputMessage inputMessage,
	                            MethodParameter parameter, Type targetType,
	                            Class<? extends HttpMessageConverter<?>> converterType) {
		this.logRequest(httpServletRequest, body);
		return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
	}

	public void logRequest(HttpServletRequest httpServletRequest, Object body) {
		if (httpServletRequest.getRequestURI().contains("medias")) {
			return;
		}
		StringBuilder data = new StringBuilder();
		data.append("[request:").append(new Gson().toJson(body)).append("]");
		log.info(data.toString());
	}

}
