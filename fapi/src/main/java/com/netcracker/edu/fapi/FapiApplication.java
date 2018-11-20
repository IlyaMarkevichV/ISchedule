package com.netcracker.edu.fapi;

import com.netcracker.edu.fapi.service.impl.AccountDataServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FapiApplication.class, args);
    }
}
