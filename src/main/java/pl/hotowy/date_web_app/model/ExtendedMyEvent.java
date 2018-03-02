package pl.hotowy.date_web_app.model;

import java.util.GregorianCalendar;

public class ExtendedMyEvent implements Comparable<ExtendedMyEvent>{

    private MyEvent event;
    private long millisLeft;

    public ExtendedMyEvent(MyEvent event) {
        this.event = event;
        updateTimeLeft();

    }

    public void updateTimeLeft(){
        millisLeft = new GregorianCalendar(event.getYear(),event.getMonth()-1,event.getDayOfMonth()).getTimeInMillis() - System.currentTimeMillis();
    }


    public MyEvent getEvent() {
        return event;
    }

    public long getMillisLeft() {
        return millisLeft;
    }

    public int getDaysLeft(){
        return (int) (millisLeft/1000/60/60/24);
    }

    @Override
    public int compareTo(ExtendedMyEvent o) {
        if (this.getMillisLeft() > o.getMillisLeft())
            return 1;
        else if (this.getMillisLeft() == o.getMillisLeft())
            return 0;
        else
            return -1;
    }
}
