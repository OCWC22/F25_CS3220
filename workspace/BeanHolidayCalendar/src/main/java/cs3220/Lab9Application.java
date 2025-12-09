package cs3220;

import cs3220.DataComponent;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Lab9Application {
    public static void main(String[] args) {
        SpringApplication.run(Lab9Application.class, args);
    }

    // Seed initial data exactly as the assignment lists
    @Bean
    CommandLineRunner init(DataComponent data) {
        return args -> {
            if (data.isEmpty()) {
                data.add("2024-01-01", "New Year's Day");
                data.add("2024-01-15", "Martin Luther King Jr. Day");
                data.add("2024-02-12", "Lincoln's Birthday");
                data.add("2024-02-19", "Presidents' Day");
                data.add("2024-07-04", "Independence Day");
                data.add("2024-09-02", "Labor Day");
                data.add("2024-11-28", "Thanksgiving Day");
                data.add("2024-12-25", "Christmas Day");
            }
        };
    }
}
