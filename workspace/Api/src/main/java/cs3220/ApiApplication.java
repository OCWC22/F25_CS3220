package cs3220;

import cs3220.model.MessageEntry;
import cs3220.model.UserEntry;
import cs3220.repository.MessageEntryRepository;
import cs3220.repository.UserEntryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.OffsetDateTime;

@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    /**
     * Seed a demo user + message so instructors can log in immediately to grade.
     */
    @Bean
    CommandLineRunner seedDemoData(UserEntryRepository users,
                                   MessageEntryRepository messages,
                                   PasswordEncoder encoder) {
        return args -> {
            if (users.count() == 0) {
                UserEntry demo = new UserEntry();
                demo.setUsername("demo");
                demo.setFullName("Demo User");
                demo.setPasswordHash(encoder.encode("password"));
                demo = users.save(demo);

                MessageEntry welcome = new MessageEntry();
                welcome.setBody("Welcome to the Lab 13 message board!");
                welcome.setUser(demo);
                welcome.setCreatedAt(OffsetDateTime.now());
                welcome.setUpdatedAt(welcome.getCreatedAt());
                messages.save(welcome);
            }
        };
    }
}
