package pl.hotowy.date_web_app.service;

import pl.hotowy.date_web_app.model.ExtendedMyEvent;
import pl.hotowy.date_web_app.model.MyEvent;

import java.util.ArrayList;
import java.util.List;

public class MyEventTasker {

    public static List<ExtendedMyEvent> prepareExtendedList(List<MyEvent> events){
        List<ExtendedMyEvent> result = new ArrayList<>();
        for (MyEvent event : events) {
            result.add(new ExtendedMyEvent(event));
        }
        return result;
    }

    public static void updateTimeLeft(List<ExtendedMyEvent> events){
        for (ExtendedMyEvent event : events) {
            event.updateTimeLeft();
        }
    }
}
