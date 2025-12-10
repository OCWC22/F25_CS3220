package cs3220.repository;

import cs3220.model.UserEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data JPA repository for UserEntry.
 */
@Repository
public interface UserEntryRepository extends JpaRepository<UserEntry, Long> {

    /**
     * Find user by email (case-insensitive).
     */
    Optional<UserEntry> findByEmailIgnoreCase(String email);

    /**
     * Check if email already exists.
     */
    boolean existsByEmailIgnoreCase(String email);
}
