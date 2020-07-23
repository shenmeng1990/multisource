package com.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MultisourceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MultisourceApplication.class, args);
        System.out.println(context);
    }

}
