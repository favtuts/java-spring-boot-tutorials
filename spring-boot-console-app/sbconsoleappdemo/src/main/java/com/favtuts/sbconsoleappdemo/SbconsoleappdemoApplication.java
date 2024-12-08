package com.favtuts.sbconsoleappdemo;

import com.favtuts.sbconsoleappdemo.services.HelloService;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SbconsoleappdemoApplication implements CommandLineRunner {

	private HelloService helloService;

	private volatile boolean keepRunning = true;

	public SbconsoleappdemoApplication(HelloService helloService) {
		this.helloService = helloService;
	}

	public static void main(String[] args) {
		log.info("Started the console application");
		SpringApplication app = new SpringApplication(SbconsoleappdemoApplication.class);
		// disable spring banner
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
		log.info("Exiting the console application");
	}

	@Override
	public void run(String... args) throws Exception {
		//runWithArguments(args);
		runIndefinitely(args);
	}

	private void runWithArguments(String... args) {
		// check if user passes any argument
		if (args.length > 0) {
			System.out.println(helloService.getMessage(args[0]));
		} else {
			// print the default message
			System.out.println(helloService.getMessage());
		}
	}

	private void runIndefinitely(String... args) {
		try {
			synchronized (this) {
				while (this.keepRunning) {
					log.info("App is running, blocking main thread until a shutdown notification");
					this.wait();
					log.info("Exiting the run() method");
				}
			}
		} catch (InterruptedException ex) {
			log.warn("Main thread interrupted. {}", ex.getMessage());
		}
	}

	@PreDestroy
	public void shutdown() {
		synchronized (this) {
			log.info("Notifying the app to stop running");
			this.keepRunning = false;
			this.notify();
		}
	}
}
