package com.arobs.internship.demointernship;

import com.arobs.internship.demointernship.service.user.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoInternshipApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);


    public static void main(String[] args) {
        SpringApplication.run(DemoInternshipApplication.class, args);
    }

}
