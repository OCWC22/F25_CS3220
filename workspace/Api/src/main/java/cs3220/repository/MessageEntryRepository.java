package cs3220.repository;

import cs3220.model.MessageEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessageEntryRepository extends JpaRepository<MessageEntry, Long> {

    List<MessageEntry> findAllByOrderByCreatedAtDesc();
    Optional<MessageEntry> findByIdAndUserId(Long id, Long userId);
}
