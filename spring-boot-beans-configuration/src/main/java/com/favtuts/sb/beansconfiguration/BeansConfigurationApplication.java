package com.favtuts.sb.beansconfiguration;

import com.favtuts.sb.beansconfiguration.service.AppService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class BeansConfigurationApplication {

	private final AppService appService;

	public BeansConfigurationApplication(AppService appService) {
		this.appService = appService;
	}

	public static void main(String[] args) {
		SpringApplication.run(BeansConfigurationApplication.class, args);
	}

	@PostConstruct
	public void doExample() {
		System.out.println("Calling bean method with this result: " + appService.calculate(123));
	}
}
