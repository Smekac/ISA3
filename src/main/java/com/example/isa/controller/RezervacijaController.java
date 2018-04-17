package com.example.isa.controller;

import com.example.isa.Model.Rezervacija;
import com.example.isa.service.RezervacijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RezervacijaController {
    @Autowired
    RezervacijaService rezervacijaService;

    @RequestMapping(value = "/rezervisi",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Rezervacija> kreirajRezeracija(@RequestBody Rezervacija rezervacija){
       if( rezervacijaService.save(rezervacija) != null)
           return new ResponseEntity<>(HttpStatus.CREATED);

       return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
