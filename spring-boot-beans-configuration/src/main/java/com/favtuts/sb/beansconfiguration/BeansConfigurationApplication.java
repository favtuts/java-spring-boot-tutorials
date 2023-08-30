package com.favtuts.sb.beansconfiguration;

import com.favtuts.sb.beansconfiguration.config.SenderMessages;
import com.favtuts.sb.beansconfiguration.service.AppService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class BeansConfigurationApplication {

	private final AppService appService;

	private final SenderMessages senderMessages;

	public BeansConfigurationApplication(AppService appService, SenderMessages senderMessages) {
		this.appService = appService;
		this.senderMessages = senderMessages;
	}

	public static void main(String[] args) {
		SpringApplication.run(BeansConfigurationApplication.class, args);
	}

	@PostConstruct
	public void doExamples() {
		System.out.println("Calling bean method with this result: " +  appService.calculate(123));

		System.out.println("Calling SenderMessages: ");
		senderMessages.send("hello universe");
	}
}
