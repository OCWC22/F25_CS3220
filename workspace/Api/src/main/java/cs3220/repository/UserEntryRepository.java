package cs3220.repository;

import cs3220.model.UserEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserEntryRepository extends JpaRepository<UserEntry, Long> {

    Optional<UserEntry> findByUsername(String username);
    boolean existsByUsername(String username);
}
