package xyz.bumbing.mvc.event.store;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SaveEventRepository extends JpaRepository<SaveEvent, Long> {
}
