package com.training.springboot.itemstorage.utils.filter;

import static com.training.springboot.itemstorage.utils.constant.ItemStorageConstant.TRACE_ID_HEADER;

import com.training.springboot.itemstorage.utils.annotation.ServiceOperation;
import com.training.springboot.itemstorage.utils.constant.ItemStorageConstant;

import java.util.Optional;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class MdcInitHandler implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		MDC.put(ItemStorageConstant.TRACE_ID,
				Optional.ofNullable(request.getHeader(TRACE_ID_HEADER)).orElse(UUID.randomUUID().toString()));
		if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			ServiceOperation serviceOperationAnnotation = handlerMethod.getMethodAnnotation(ServiceOperation.class);
			MDC.put(ItemStorageConstant.OPERATION, serviceOperationAnnotation.value());
			return true;
		}
		return true;
	}

}
