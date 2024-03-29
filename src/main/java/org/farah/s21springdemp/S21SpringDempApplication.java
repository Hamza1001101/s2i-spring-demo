package org.farah.s21springdemp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
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

}

record Fruits(String name, String description) {

}