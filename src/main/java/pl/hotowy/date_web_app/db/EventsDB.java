package pl.hotowy.date_web_app.db;

import org.springframework.stereotype.Repository;
import pl.hotowy.date_web_app.model.MyEvent;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EventsDB {

    List<MyEvent> database;

    public EventsDB() {
        this.database = new ArrayList<>();
        database.add(new MyEvent("asd",15,12,2018,"desc"));
        database.add(new MyEvent("asd",15,12,2012,"desc"));
        database.add(new MyEvent("asd",15,12,2022,"desc"));
        database.add(new MyEvent("asd",12,2,2018,"desc"));
    }

    public List<MyEvent> getDatabase() {
        return database;
    }
}
