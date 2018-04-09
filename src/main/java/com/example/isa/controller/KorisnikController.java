package com.example.isa.controller;

import com.example.isa.DTO.Credentials;
import com.example.isa.DTO.KorisnikDTO;
import com.example.isa.Model.Korisnici.Korisnik;
import com.example.isa.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@RestController
public class KorisnikController {

    @Autowired
    KorisnikService korisnikService;


    //TODO: dodati provjeru ako je regularni da li je potvrdio mail
    @RequestMapping(
            value = "/logIn",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody Credentials credentials){
        Korisnik korisnik = korisnikService.findByEmailAndPassword(credentials.getEmail(), credentials.getPassword());
        if(korisnik == null)
            return new ResponseEntity<String> ("Pogresni kredencijali", HttpStatus.BAD_REQUEST);

        ServletRequestAttributes attr = (ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes();
        HttpSession session= attr.getRequest().getSession(true);

        session.setAttribute("korisnik", korisnik);


        return  new ResponseEntity<String>( "Logovanje je uspjesno", HttpStatus.OK);

    }
}
