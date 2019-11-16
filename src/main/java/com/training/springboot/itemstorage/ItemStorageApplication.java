package com.training.springboot.itemstorage;

import com.training.springboot.itemstorage.utils.filter.LoggingHandler;
import com.training.springboot.itemstorage.utils.filter.MdcInitHandler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ItemStorageApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemStorageApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer initializerWebMvcConfigurer(MdcInitHandler mdcInitHandler, LoggingHandler loggingHandler) {
		return new WebMvcConfigurer() {
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				registry.addInterceptor(mdcInitHandler);
				registry.addInterceptor(loggingHandler);
			}
		};
	}

}
