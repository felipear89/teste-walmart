package com.farodrigues.teste_walmart;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "com.farodrigues.teste_walmart" })
@EnableAutoConfiguration
public class App {

	public static void main(String[] args) throws Exception {
		new SpringApplicationBuilder(App.class).showBanner(false).run(args);
	}

}
