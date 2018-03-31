package com.example.isa.controller;

import com.example.isa.Model.Korisnici.RegPosetilacModel;
import com.example.isa.service.RegPosetilacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.core.env.Environment;
import java.util.List;

/**
 * Created by Smekac on 2/2/2018.
 */
@RestController
public class RegPosetilacController {

    @Autowired
    private RegPosetilacService regPosetilacService;

    @Autowired
    private Environment env;
    @Autowired
    private JavaMailSender mailSender;

    @RequestMapping(
            value = "/getRegPosetilac",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RegPosetilacModel>> getRegisteredUsers() {
        List<RegPosetilacModel> RegisteredUsers = regPosetilacService.findAll();
        return new ResponseEntity<>(RegisteredUsers, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/createRegPosetilac",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegPosetilacModel> createRegisteredUser(@RequestBody RegPosetilacModel RegisteredUser) {

        RegPosetilacModel savedRegisteredUser = regPosetilacService.save(RegisteredUser);
        sendEmai(RegisteredUser);
        return new ResponseEntity<>(savedRegisteredUser, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/updateRegPosetilac",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegPosetilacModel> updateRegisteredUser(@RequestBody RegPosetilacModel RegisteredUser) {
        RegPosetilacModel updatedRegisteredUser = regPosetilacService.save(RegisteredUser);
        return new ResponseEntity<>(updatedRegisteredUser, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/deleteRegPosetilac/{id}",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegPosetilacModel> deleteRegisteredUser(@PathVariable("id") Long id) {
        regPosetilacService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    public boolean sendEmai(RegPosetilacModel regPosetilac){

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(regPosetilac.getEmail());

        mail.setSubject("Potvrda mail adrese");

        mail.setText("http://localhost:8080/potvrdaMaila/"+regPosetilac.getId());

        mail.setTo(regPosetilac.getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));


        try {
            mailSender.send(mail);
        }catch (MailException mex){
            System.out.println(mex);
        }
        System.out.println("Poslao na " + regPosetilac.getEmail());
        return false;
    }

}
