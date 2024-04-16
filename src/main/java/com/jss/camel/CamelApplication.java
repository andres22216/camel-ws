package com.jss.camel;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.jss"})
public class CamelApplication {

	@Autowired
	ProducerTemplate template;

	public static void main(String[] args) {
		SpringApplication.run(CamelApplication.class, args);
	}

}
