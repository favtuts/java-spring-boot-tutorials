package com.favtuts.sb.jobscheduling.jobs;

import org.springframework.scheduling.annotation.Async;
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

    //@Scheduled(fixedDelay = 2000)
    public void computePriceFixedDelay() throws InterruptedException {
        LOGGER.info("fixed delay computing price at " + LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));

        Random random = new Random();
        price = random.nextDouble() * 100;

        // added sleep to simulate method
        // which takes longer to execute.
        Thread.sleep(4000);

        // The new job will always wait for the previous job to finish
    }


    //@Scheduled(fixedRate = 3000)
    public void computePriceFixedRate() throws InterruptedException {
        LOGGER.info("fixed rate computing price at " + LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));

        Random random = new Random();
        price = random.nextDouble() * 100;

        /*
        We use the fixedRate attribute to specify the interval for executing a job at a fixed interval of time.
        It should be used in situations where method invocations are independent.
        The execution time of the method is not taken into consideration when deciding when to start the next job.
         */

        /*
        Without applying @Async annotation, the method will always execute after the previous execution is completed,
        even if the fixed-rate interval is expired.
         */
        Thread.sleep(8000);
    }

    //@Scheduled(fixedRate = 3000)
    @Async
    public void computePriceFixedRateAsync() throws InterruptedException {
        LOGGER.info("fixed rate async computing price at " + LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));

        Random random = new Random();
        price = random.nextDouble() * 100;

        /*
        The @Async annotation over a method allows it to execute in a separate thread.
        As a result of this, when the previous execution of the method takes longer than the fixed-rate interval,
        the subsequent invocation of a method will trigger even if the previous invocation is still executing.
         */

        Thread.sleep(8000);
    }

    //@Scheduled(initialDelay = 10000, fixedRate = 3000)
    @Async
    public void computePriceFixedRateAsyncInitialDelay() throws InterruptedException {
        LOGGER.info("fixed rate async initial delay computing price at " + LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));

        Random random = new Random();
        price = random.nextDouble() * 100;

        Thread.sleep(8000);
    }

    // @Scheduled(fixedDelayString = "PT02S")
    public void computePriceFixedDelayISODurationFormat() throws InterruptedException {
        LOGGER.info("fixed delay iso duration computing price at " + LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));

        Random random = new Random();
        price = random.nextDouble() * 100;

        // added sleep to simulate method
        // which takes longer to execute.
        Thread.sleep(4000);
    }

    //@Scheduled(fixedRateString = "PT03S")
    public void computePriceFixedRateISODurationFormat() throws InterruptedException {
        LOGGER.info("fixed rate iso duration computing price at " + LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));

        Random random = new Random();
        price = random.nextDouble() * 100;

        // added sleep to simulate method
        // which takes longer to execute.
        Thread.sleep(8000);
    }


    //@Scheduled(fixedDelayString = "${interval-fixed-delay}")
    public void computePriceFixedDelayISODurationFormatConfig() throws InterruptedException {
        LOGGER.info("fixed delay iso duration config computing price at " + LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));

        Random random = new Random();
        price = random.nextDouble() * 100;

        // added sleep to simulate method
        // which takes longer to execute.
        Thread.sleep(4000);
    }

    //@Scheduled(fixedRateString = "${interval-fixed-rate}")
    public void computePriceFixedRateISODurationFormatConfig() throws InterruptedException {
        LOGGER.info("fixed rate iso duration config computing price at " + LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));

        Random random = new Random();
        price = random.nextDouble() * 100;

        // added sleep to simulate method
        // which takes longer to execute.
        Thread.sleep(8000);
    }

    //Cron=0 * * * * * -> every minutes
    //@Scheduled(cron = "${interval-in-cron}")
    public void computePriceCronExpression() throws InterruptedException {
        LOGGER.info("cron expression computing price at " + LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));

        Random random = new Random();
        price = random.nextDouble() * 100;

        // added sleep to simulate method
        // which takes longer to execute.
        Thread.sleep(4000);
    }

    @Scheduled(cron = "@hourly")
    public void computePriceCronMacro() throws InterruptedException {
        LOGGER.info("cron macro computing price at " + LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));

        Random random = new Random();
        price = random.nextDouble() * 100;

        // added sleep to simulate method
        // which takes longer to execute.
        Thread.sleep(4000);
    }
}
