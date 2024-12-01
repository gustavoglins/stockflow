package com.stockflow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StockflowApplication {

    private static final Logger logger = LoggerFactory.getLogger(StockflowApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(StockflowApplication.class, args);
        logger.info("StockFlow Started");
    }

}
