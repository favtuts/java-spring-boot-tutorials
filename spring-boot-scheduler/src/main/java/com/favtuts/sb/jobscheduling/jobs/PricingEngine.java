package com.favtuts.sb.jobscheduling.jobs;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Random;
import java.util.logging.Logger;

@Service
public class PricingEngine {
    static final Logger LOGGER = Logger.getLogger(PricingEngine.class.getName());
    private Double price;

    public Double getProductPrice() {
        return price;
    }

    @Scheduled(fixedDelay = 2000)
    public void computePrice() throws InterruptedException {
        LOGGER.info("computing price at " + LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));

        Random random = new Random();
        price = random.nextDouble() * 100;

        // added sleep to simulate method
        // which takes longer to execute.
        Thread.sleep(4000);
    }
}
