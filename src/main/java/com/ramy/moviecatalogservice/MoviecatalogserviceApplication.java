package com.ramy.moviecatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class MoviecatalogserviceApplication {


    @Bean
    @LoadBalanced//adds service discovery //
    public RestTemplate getRestTemplate(){
        return  new RestTemplate();//because of @LoadBalanced, RestTemplate will look for hints about which service to call when given the url
    }

    public static void main(String[] args) {
        SpringApplication.run(MoviecatalogserviceApplication.class, args);
    }

}
