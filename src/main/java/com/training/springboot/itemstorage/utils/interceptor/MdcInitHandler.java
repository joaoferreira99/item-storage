package com.training.springboot.itemstorage.utils.interceptor;

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
		// TODO: Add TRACE_ID to the logs using the MDC (Mapped Diagnostic Context)
		if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			// TODO: Add OPERATION to the logs using the MDC (Mapped Diagnostic Context). HINT: you can retrieve information in the annotation ServiceOperation
		}
		return true;
	}

}