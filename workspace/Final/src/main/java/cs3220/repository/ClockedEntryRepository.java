package cs3220.repository;

import cs3220.model.ClockedEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for ClockedEntry.
 */
@Repository
public interface ClockedEntryRepository extends JpaRepository<ClockedEntry, Long> {

    /**
     * Find all entries ordered by timestamp ascending (oldest first).
     */
    List<ClockedEntry> findAllByOrderByTimestampAsc();
}
