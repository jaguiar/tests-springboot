package org.yaourtcorp.testspringboot.web;

import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.yaourtcorp.testspringboot.service.AddService;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class TestContext {

	@Bean
	public AddService todoService() {
		return Mockito.mock(AddService.class);
	}
}
