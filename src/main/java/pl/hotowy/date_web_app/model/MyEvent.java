package pl.hotowy.date_web_app.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class MyEvent {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User owner;

    private String title;
    private int dayOfMonth;
    private int month;
    private int year;
    private String description;

    public MyEvent(String title, int dayOfMonth, int month, int year, String description) {
        this.title = title;
        this.dayOfMonth = dayOfMonth;
        this.month = month;
        this.year = year;
        this.description = description;
    }
    public MyEvent(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
