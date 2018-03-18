package pl.hotowy.date_web_app.service;

import pl.hotowy.date_web_app.model.MyEvent;

import java.util.List;

public abstract class EventsToTextConverter {

    public static String convert(List<MyEvent> list){
        StringBuilder result = new StringBuilder("  LISTA WYDARZEN");
        for (MyEvent event : list) {
            result.append("\n\n - "+event.getTitle()+" : "+event.getDayOfMonth()+"."+event.getMonth()+"."+event.getYear());
        }
        String resultS = result.toString();
        return resultS;
    }
}
