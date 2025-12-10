package cs3220.config;

import cs3220.model.ClockedEntry;
import cs3220.model.UserEntry;
import cs3220.repository.ClockedEntryRepository;
import cs3220.repository.UserEntryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Seeds demo data on application startup if database is empty.
 */
@Component
public class DataSeeder implements CommandLineRunner {

    private final UserEntryRepository userRepo;
    private final ClockedEntryRepository clockedRepo;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(UserEntryRepository userRepo,
                      ClockedEntryRepository clockedRepo,
                      PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.clockedRepo = clockedRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Only seed if no users exist
        if (userRepo.count() > 0) {
            System.out.println("Database already seeded, skipping...");
            return;
        }

        System.out.println("Seeding demo data...");

        // Create demo users
        UserEntry tom = createUser("tom@example.com", "Tom", "password");
        UserEntry jane = createUser("jane@example.com", "Jane", "password");
        UserEntry john = createUser("john@example.com", "John", "password");

        // Create clock entries matching the screenshot
        LocalDateTime baseTime = LocalDateTime.of(2025, 5, 6, 2, 1);

        // Tom clocks in at 2:01
        createClockEntry(tom, baseTime, "Clock In");

        // Jane clocks in at 2:01
        createClockEntry(jane, baseTime, "Clock In");

        // Jane clocks out at 2:01
        createClockEntry(jane, baseTime, "Clock Out");

        // Tom clocks out at 2:02
        createClockEntry(tom, baseTime.plusMinutes(1), "Clock Out");

        // John clocks in at 2:08
        createClockEntry(john, baseTime.plusMinutes(7), "Clock In");

        // John clocks out at 2:08
        createClockEntry(john, baseTime.plusMinutes(7), "Clock Out");

        System.out.println("Demo data seeded successfully!");
        System.out.println("Demo users: tom@example.com, jane@example.com, john@example.com (password: password)");
    }

    private UserEntry createUser(String email, String name, String password) {
        UserEntry user = new UserEntry();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(passwordEncoder.encode(password));
        return userRepo.save(user);
    }

    private void createClockEntry(UserEntry user, LocalDateTime timestamp, String action) {
        ClockedEntry entry = new ClockedEntry();
        entry.setUser(user);
        entry.setTimestamp(timestamp);
        entry.setAction(action);
        clockedRepo.save(entry);
    }
}
