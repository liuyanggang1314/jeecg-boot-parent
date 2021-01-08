package org.jeecg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LetourTicketsApplication {

    public static void main(String[] args) {
        SpringApplication.run(LetourTicketsApplication.class, args);
    }
}
