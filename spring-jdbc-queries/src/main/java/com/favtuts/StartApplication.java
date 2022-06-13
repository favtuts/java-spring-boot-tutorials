package com.favtuts;

import com.favtuts.customer.Customer;
import com.favtuts.customer.CustomerRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class StartApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(StartApplication.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    CustomerRepository customerRepository;

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }

    @Override
    public void run(String... args) {

        log.info("StartApplication...");

        startCustomerApp();

    }

    // Tested with H2 database
    void startCustomerApp() {

        jdbcTemplate.execute("DROP TABLE customer IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE customer(" +
                "id SERIAL, name VARCHAR(255), age NUMERIC(2), created_date timestamp)");

        List<Customer> list = Arrays.asList(
                new Customer("Customer A", 19),
                new Customer("Customer B", 20),
                new Customer("Customer C", 21),
                new Customer("Customer D", 22)
        );

        list.forEach(x -> {
            log.info("Saving...{}", x.getName());
            customerRepository.save(x);
        });

        log.info("[FIND_BY_ID]");
        log.info("{}", customerRepository.findByCustomerId(1L));
        log.info("{}", customerRepository.findByCustomerId2(2L));
        log.info("{}", customerRepository.findByCustomerId3(3L));

        log.info("[FIND_ALL]");
        log.info("{}", customerRepository.findAll());
        log.info("{}", customerRepository.findAll2());
        log.info("{}", customerRepository.findAll3());
        log.info("{}", customerRepository.findAll4());

        log.info("[FIND_NAME_BY_ID]");
        log.info("{}", customerRepository.findCustomerNameById(4L));

        log.info("[COUNT]");
        log.info("{}", customerRepository.count());

    }

}