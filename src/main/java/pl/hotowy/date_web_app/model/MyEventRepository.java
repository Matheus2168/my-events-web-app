package pl.hotowy.date_web_app.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyEventRepository extends JpaRepository<MyEvent,Long> {

    List<MyEvent> findAllByOwner(User owner);
}
