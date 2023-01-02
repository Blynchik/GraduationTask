package ru.javaops.topjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.javaops.topjava.repository.RestaurantRepository;

@SpringBootApplication
public class CaloriesManagementRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(CaloriesManagementRestApplication.class, args);
    }
}
