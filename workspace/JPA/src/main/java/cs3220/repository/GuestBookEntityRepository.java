package cs3220.repository;

import cs3220.model.GuestBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuestBookEntityRepository extends JpaRepository<GuestBookEntity, Integer> {

    List<GuestBookEntity> findAllByOrderByDateDesc();
}
