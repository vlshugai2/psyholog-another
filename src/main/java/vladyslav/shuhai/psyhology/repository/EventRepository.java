package vladyslav.shuhai.psyhology.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vladyslav.shuhai.psyhology.entity.Event;

public interface EventRepository extends JpaRepository<Event,Long> {
}
