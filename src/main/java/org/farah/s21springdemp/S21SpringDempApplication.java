package org.farah.s21springdemp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.awt.*;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class S21SpringDempApplication {

    public static void main(String[] args) {
        SpringApplication.run(S21SpringDempApplication.class, args);
    }

}


@RestController
class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @GetMapping
    private String helloworld() {
        LOGGER.info("fetched data:::::::::::::");
        return "hello world";
    }

    List<Fruits> fruits = List.of(new Fruits("Mongo", "Tropical Fruit"), new Fruits("Ananas", "Summer Fruits"));

    @GetMapping("/fruits")
    private List<Fruits> getAll() {
        LOGGER.info("all fruits coming, {}", fruits);
        return fruits;
    }


    RestClient restClient = RestClient.create();

    @Scheduled(cron = "* * * ? * *")
    @GetMapping("/hello")
    private void clientScheduler() {

        String body = restClient.get()
                .uri("https://s2i-spring.apps.sandbox-m2.ll9k.p1.openshiftapps.com/fruits")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(String.class);
        LOGGER.info("coming here...., {}", body);

    }
}

record Fruits(String name, String description) {

}