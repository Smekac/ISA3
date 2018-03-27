package com.example.isa.controller;

import com.example.isa.Model.Korisnici.RegPosetilacModel;
import com.example.isa.service.RegPosetilacService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Smekac on 2/2/2018.
 */
public class RegPosetilacController {

    private RegPosetilacService regPosetilacService;

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


}
