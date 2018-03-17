package pl.hotowy.date_web_app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.hotowy.date_web_app.db.EventsDB;
import pl.hotowy.date_web_app.model.*;
import pl.hotowy.date_web_app.service.EncodeOperator;
import pl.hotowy.date_web_app.service.MyEventTasker;

import java.security.Principal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private EventsDB database;
    @Autowired
    UserRepository users;
    @Autowired
    UserRoleRepository roles;
    @Autowired
    MyEventRepository events;


    @RequestMapping("/")
    public String indexpage(Principal user, Model model) {
        model.addAttribute("user", user);
        User owner = users.findByUsername(user.getName());
        List<MyEvent> events = this.events.findAllByOwner(owner);
        List<ExtendedMyEvent> extendedMyEvents = MyEventTasker.prepareExtendedList(events);
        MyEventTasker.updateTimeLeft(extendedMyEvents);
        Collections.sort(extendedMyEvents);
        model.addAttribute("events", extendedMyEvents);
        return "index";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        List<ExtendedMyEvent> extendedMyEvents = MyEventTasker.prepareExtendedList(database.getDatabase());
        Collections.sort(extendedMyEvents);
        model.addAttribute("events", extendedMyEvents);
        return "list";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam(value = "username") String username,
                           @RequestParam(value = "password") String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        EncodeOperator.encodePassword(user);
        user.setEnabled(true);


        UserRole role = new UserRole();
        role.setUsername(user.getUsername());
        role.setRole("user");

        if (users.findByUsername(user.getUsername()) == null) {
            users.save(user);
            roles.save(role);
        }

        return "redirect:/login";
    }

    @PostMapping("/addEvent")
    public String addEvent(@RequestParam(value = "title") String title,
                           @RequestParam(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") String date,
                           @RequestParam(value = "description") String description,
                           Principal principal) {
        String[] splitDate = date.split("-");
        MyEvent myEvent = new MyEvent();
        myEvent.setYear(Integer.parseInt(splitDate[0]));
        myEvent.setMonth(Integer.parseInt(splitDate[1]));
        myEvent.setDayOfMonth(Integer.parseInt(splitDate[2]));
        myEvent.setTitle(title);
        myEvent.setDescription(description);
        myEvent.setOwner(users.findByUsername(principal.getName()));
        events.save(myEvent);

        return "redirect:/";
    }

    @GetMapping("/delete/event/{id}")
    public String deleteEvent(Principal user, @PathVariable Long id) {
        if (user.getName().equals(events.findOne(id).getOwner().getUsername())) {
            events.delete(id);
        }
        return "redirect:/";

    }

    @PostMapping("/edit/{id}")
    public String editEvent(Principal user, @PathVariable Long id,
                            @RequestParam(value = "title") String title,
                            @RequestParam(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") String date,
                            @RequestParam(value = "description") String description){
        if (user.getName().equals(events.findOne(id).getOwner().getUsername())) {
            MyEvent myEvent = events.getOne(id);

            String[] splitDate = date.split("-");
            myEvent.setYear(Integer.parseInt(splitDate[0]));
            myEvent.setMonth(Integer.parseInt(splitDate[1]));
            myEvent.setDayOfMonth(Integer.parseInt(splitDate[2]));
            myEvent.setTitle(title);
            myEvent.setDescription(description);
            events.save(myEvent);


        }
        return "redirect:/";

    }
}
