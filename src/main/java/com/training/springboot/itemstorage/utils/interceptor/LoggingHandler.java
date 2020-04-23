package com.training.springboot.itemstorage.utils.interceptor;

import static com.training.springboot.itemstorage.utils.constant.ItemStorageConstant.LOGGING_HANDLER_PROCESS_TIME_MSG;

import com.training.springboot.itemstorage.utils.constant.ItemStorageConstant;
import java.time.Instant;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class LoggingHandler implements HandlerInterceptor {

	// Use this to start and stop a timer, and log that information.
	private StopWatch stopWatch;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		/**
		 * TODO: Start the watch and log request as an INFO using the format specified in ItemStorageConstant.LOGGING_HANDLER_INBOUND_MSG
		 * 	example: Received HTTP [POST] Request to [/items] at [2020-04-23T16:13:42.148Z]
		 */
		return true;
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) {
		/**
		 * TODO: log response as an INFO using the format specified in ItemStorageConstant.LOGGING_HANDLER_OUTBOUND_MSG
		 * 	example: Responded with Status [201] at [2020-04-23T16:13:42.412Z]
		 */

		/**
		 * TODO: Stop the watch and log the total time in milliseconds
		 * 	example: Total processing time [262] ms
		 */
	}

}