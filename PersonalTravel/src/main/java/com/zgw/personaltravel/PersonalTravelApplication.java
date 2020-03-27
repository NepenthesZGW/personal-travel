package com.zgw.personaltravel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PersonalTravelApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonalTravelApplication.class, args);
    }


    @Bean
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }
}
