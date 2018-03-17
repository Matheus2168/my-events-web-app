package pl.hotowy.date_web_app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.hotowy.date_web_app.db.EventsDB;
import pl.hotowy.date_web_app.model.*;
import pl.hotowy.date_web_app.service.EncodeOperator;
import pl.hotowy.date_web_app.service.MyEventTasker;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@RestController
public class ApiController {

    @Autowired private EventsDB database;
    @Autowired UserRepository users;
    @Autowired UserRoleRepository roles;
    @Autowired MyEventRepository events;



    @GetMapping("/api/verifyUsername/{login}")
    @ResponseBody
    public boolean verifyUsername(@PathVariable String login){

       User user = users.findByUsername(login);
       if (user == null){
           return true;
       }
       else return false;
    }

    @GetMapping("/api/get/{id}")
    @ResponseBody
    public MyEvent getMyEvent(@PathVariable Long id){
        return events.getOne(id);
    }

    @DeleteMapping("/api/delete/event/{id}")
    @Modifying
    public void deleteEvent(@PathVariable Long id){
        events.delete(id);
    }


}
