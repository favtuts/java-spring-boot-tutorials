package com.favtuts.sbconsoleappdemo;

import com.favtuts.sbconsoleappdemo.services.HelloService;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SbconsoleappdemoApplication implements CommandLineRunner {

	private HelloService helloService;

	public SbconsoleappdemoApplication(HelloService helloService) {
		this.helloService = helloService;
	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SbconsoleappdemoApplication.class);
		// disable spring banner
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		// check if user passes any argument
		if (args.length > 0) {
			System.out.println(helloService.getMessage(args[0]));
		} else {
			// print the default message
			System.out.println(helloService.getMessage());
		}
	}
}
