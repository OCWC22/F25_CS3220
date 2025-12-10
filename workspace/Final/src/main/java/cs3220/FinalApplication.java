package cs3220;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for CS3220 Final - Time Clock Application.
 * Uses Spring Boot with FreeMarker, JPA (MySQL), and REST APIs.
 */
@SpringBootApplication
public class FinalApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinalApplication.class, args);
    }
}
