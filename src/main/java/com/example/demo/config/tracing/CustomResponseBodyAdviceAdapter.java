package com.example.demo.config.tracing;

import com.example.demo.constant.DemoConstant;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Slf4j
@ControllerAdvice
public class CustomResponseBodyAdviceAdapter implements ResponseBodyAdvice<Object> {

	@Override
	public boolean supports(MethodParameter methodParameter,
	                        Class<? extends HttpMessageConverter<?>> aClass) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body,
	                              MethodParameter methodParameter,
	                              MediaType mediaType,
	                              Class<? extends HttpMessageConverter<?>> aClass,
	                              ServerHttpRequest serverHttpRequest,
	                              ServerHttpResponse serverHttpResponse) {

		if (serverHttpRequest instanceof ServletServerHttpRequest &&
						serverHttpResponse instanceof ServletServerHttpResponse) {
			this.logResponse(
							((ServletServerHttpRequest) serverHttpRequest).getServletRequest(),
							((ServletServerHttpResponse) serverHttpResponse).getServletResponse(), body);
		}
		return body;
	}

	public void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object body) {
		if (httpServletRequest.getRequestURI().contains("medias")) {
			return;
		}
		StringBuilder data = new StringBuilder();
		try {
			data.append("[response:").append(new Gson().toJson(body)).append("]");
		}
		catch (Exception exception) {
			data.append("[response:").append("--").append("]");
		}
		finally {
			Long requestTime = (Long)httpServletRequest.getAttribute(DemoConstant.Tracing.TIME_REQUEST);
			if (!Objects.isNull(requestTime)) {
				data.append(", processing time: ")
								.append(System.currentTimeMillis() - requestTime)
								.append(" ms");
			}
		}

		log.info(data.toString());
	}
}
