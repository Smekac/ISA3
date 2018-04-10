package com.example.isa.controller;

import com.example.isa.Model.Korisnici.RegPosetilacModel;
import com.example.isa.service.RegPosetilacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
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



    @RequestMapping(
            value = "/getRegPosetilac",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RegPosetilacModel>> getRegisteredUsers() {
        List<RegPosetilacModel> RegisteredUsers = regPosetilacService.findAll();
        return new ResponseEntity<>(RegisteredUsers, HttpStatus.OK);
    }


    //1. registracija korisnika
    @RequestMapping(
            value = "/createRegPosetilac",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createRegisteredUser(@Validated @RequestBody RegPosetilacModel newUser,Errors errors ) {

        if(errors.hasErrors())
        {
            return  new ResponseEntity<String>(errors.getAllErrors().toString(), HttpStatus.BAD_REQUEST);
        }

        RegPosetilacModel savedRegisteredUser = regPosetilacService.save(newUser);
        regPosetilacService.sendEmai(savedRegisteredUser);

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


    @RequestMapping(value = "/potvrdaMaila/{id}",method = RequestMethod.GET)
    public void potvrdaEmailAdrese(@PathVariable("id") String idKorisnika){
        Long id = Long.parseLong(idKorisnika);
        regPosetilacService.confirmEmailAdress(id);

    }


}
