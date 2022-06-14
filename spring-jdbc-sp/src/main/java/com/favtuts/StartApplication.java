package com.favtuts;

import com.favtuts.repository.BookRepository;
import com.favtuts.sp.StoredFunction;
import com.favtuts.sp.StoredProcedure1;
import com.favtuts.sp.StoredProcedure2;
import com.favtuts.sp.TestData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    StoredProcedure1 storedProcedure1;

    @Autowired
    StoredProcedure2 storedProcedure2;

    @Autowired
    StoredFunction storedFunction;

    @Autowired
    TestData testData;    

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }

    @Override
    public void run(String... args) {
        log.info("StartApplication...");

        // To test Stored Procedure and Function
        // Uncomment Oracle JDBC in pom.xml and define datasource in application.properties
        testData.start();
        storedProcedure1.start();
        storedProcedure2.start();
        storedFunction.start();
    }
}